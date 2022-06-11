package co.edu.unbosque.rest.services;

import co.edu.unbosque.rest.model.Art;
import co.edu.unbosque.rest.model.UserApp;
import co.edu.unbosque.rest.model.Wallet;
import co.edu.unbosque.rest.services.;

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
                List<Art> arts= service.listaobra();

   /*             String query1 = "UPDATE ownership SET userApp = '" + email +  "' WHERE art = '" + art + "'";
                stmt.execute(query1);*/

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
}
