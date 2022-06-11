package co.edu.unbosque.restsql.services;
import co.edu.unbosque.restsql.model.Artist;
import co.edu.unbosque.restsql.model.Person;
import co.edu.unbosque.restsql.model.Shopper;
import java.sql.*;
import java.util.*;

public class UserServices {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://localhost/StratHeroas";
    static final String USER = "postgres";
    static final String PASSWORD = "admin";

    public UserServices(){}

    public Person addElement(String name, String email, String password, String role){
        System.out.println("Name = " + name + ", email = " + email + ", password = " + password + ", role = " + role);
        Person response = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            System.out.println("name = " + name + ", email = " + email + ", password = " + password + ", role = " + role);
            String query = "INSERT INTO Person(name, email, password, role) VALUES('" + name +"', '" + email +"', '" + password +"', '" + role +"')";
            statement.execute(query);
            if(role.equals("Artist")) {
                query = "INSERT  INTO Artist(name) VALUES('" + name + "')";
                response = new Artist(name, email, password, role , "La pintura es mas fuerte que yo");
            }else {
                query = "INSERT INTO Shopper(name, fcoins) VALUES('" + name + "')";
                response = new Shopper(name, email, password, role,0);
            }
            statement.execute(query);
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return response;
    }



    public List <Person> getList(String username, String password){
        List <Person> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            String query = "SELECT  * FROM Person";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                String name = result.getString("name");
                String email = result.getString("email");
                String key = result.getString("password");
                String role = result.getString("role");
                if(optionalNull(role))
                    list.add(new Artist(name.equals("null") ? "vacio" : name, email, key, "Artist", ""));
                else
                    list.add(new Shopper(name.equals("null") ? "vacio" : name, email, key, "Shopper",0));
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return list;
    }



    public Person updateFcoins(String username, double fcoins){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            String query = "UPDATE Artist SET 2 = '" + fcoins + "' WHERE name = '" + username + "'";
            statement.execute(query);
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return null;
    }

    private boolean optionalNull(String role){
        return Optional.ofNullable(role).orElse("Artist").equalsIgnoreCase("Artist");
    }
}
