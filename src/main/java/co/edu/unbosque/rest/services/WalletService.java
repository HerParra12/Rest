package co.edu.unbosque.rest.services;

import co.edu.unbosque.rest.model.UserApp;
import co.edu.unbosque.rest.model.Wallet;

import java.sql.*;
import java.util.Date;

public class WalletService {
    public WalletService() {
    }

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://localhost/Laschiquistriquis";
    static final String USER = "postgres";
    static final String PASSWORD = "admin";

    public void sell(String username,String paswword,String fcoins){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            UserApp users = new UserService().login(username, paswword);
            String email= users.getEmail();
            String type = "recharge";
            Date date = new Date();
            long mili = date.getTime();
            Timestamp time = new Timestamp(mili);

            //UPDATE wallet SET name = 'juan';
            Wallet user1= new Wallet(email,type,Float.parseFloat(fcoins));
            String query = "UPDATE wallethistory SET fcoins = '" + fcoins +  "' WHERE userapp = '" + email + "'";
            stmt.execute(query);
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
