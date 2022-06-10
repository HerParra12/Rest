package co.edu.unbosque.restsql.model;

public class Shopper extends Person{

    public Shopper(){}

    public Shopper(String nombre, String correo, String clave, String role, double coins) {
        super(nombre, correo, clave, role, coins);
    }

    public String getUsername() {
        return super.username;
    }

    public void setUsername(String username) {
        super.username = username;
    }

    public String getEmail() {
        return super.email;
    }

    public void setEmail(String email) {
        super.email = email;
    }

    public String getPassword() {
        return super.password;
    }

    public void setPassword(String password) {
        super.password = password;
    }

    public double getFcoins() {
        return super.fcoins;
    }

    public void setFcoins(double fcoins) {
        super.fcoins = fcoins;
    }
}
