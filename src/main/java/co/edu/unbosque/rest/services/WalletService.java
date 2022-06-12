package co.edu.unbosque.rest.services;

import co.edu.unbosque.rest.model.Art;
import co.edu.unbosque.rest.model.UserApp;


import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class WalletService {
    public WalletService() {
    }

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://localhost/Laschiquistriquis";
    static final String USER = "postgres";
    static final String PASSWORD = "admin";

    public Optional<Boolean> sell(String username, String paswword, String fcoins, String nameart) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            UserApp users = new UserService().login(username, paswword);
            String email = users.getEmail();
            float Fcoins = users.getFcoins() - Float.parseFloat(fcoins);
            String type = "buy";
            Date date = new Date();
            long mili = date.getTime();
            Timestamp time = new Timestamp(mili);
            if (Fcoins > 0) {
                String query = "UPDATE wallethistory SET fcoins = '" + Fcoins + "' WHERE userapp = '" + email + "'";
                stmt.execute(query);
                ArtService service= new ArtService(conn);
                Art arts= service.listaobra1(nameart);
                service.updateobra(arts);

            } else {
                return Optional.of(false);
            }

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
        return Optional.of(true);

    }

    /*public void sold(String username, String Fcoins){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            List<Art> users = new ArtService(conn).listaobra();
            Art user1 = users.stream().filter(user-> username.equals(user.getTitle())).findFirst().orElse(null);
            List<UserApp> user = new UserService().getUsers(user1.getOwner());
            UserApp us=  user.stream().filter(use-> username.equals(use.getName())).findFirst().orElse(null);
            float numFcoins = Float.parseFloat(Fcoins);
            String type = "sale";
            Date date = new Date();
            long mili = date.getTime();
            Timestamp time = new Timestamp(mili);

            String sql = "INSERT INTO Wallet_table (email,password,total) VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user1.getUsername());
            stmt.setInt(2, Integer.parseInt(user1.getPassword()));
            stmt.setFloat(3,numFcoins);

            stmt.executeUpdate();

            stmt.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }*/
}
