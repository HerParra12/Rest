package co.edu.unbosque.rest.services;
import co.edu.unbosque.rest.model.UserApp;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://localhost/Laschiquistriquis";
    static final String USER = "postgres";
    static final String PASSWORD = "admin";

    public UserService(){}

    public UserApp addElement(String name, String email, String password, String role){
        UserApp response = new UserApp(name, email, password, role,0);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            System.out.println("name = " + name + ", email = " + email + ", password = " + password + ", role = " + role);
            String query = "INSERT INTO userApp(name, email, password, role) VALUES ('" + name +"', '" + email +"', '" + password +"', '" + role +"')";
            String query2 = "INSERT INTO Art(name, email, password, role) VALUES ('" + name +"', '" + email +"', '" + password +"', '" + role +"')";
            statement.execute(query);

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return response;
    }



    public UserApp login(String username, String password){
        List <UserApp> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            String query = "SELECT  * FROM userApp";
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                list.add(new UserApp(result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role"),
                        result.getFloat("focins")));
            }
            return list.stream()
                       .peek(x -> System.out.println(x))
                       .filter(x -> x.getName().equals(username) && x.getPassword().equals(password))
                       .findFirst()
                       .orElse(new UserApp());

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return new UserApp();
    }

    public List<UserApp> getUsers(String username) {
        List <UserApp> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            String query = "SELECT  * FROM userApp";
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                list.add(new UserApp(result.getString("name"),
                       result.getString("email"),
                        result.getString("password"),
                        result.getString("role"),
                        result.getFloat("focins")));
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
