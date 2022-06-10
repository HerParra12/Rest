package co.edu.unbosque.restsql.model;

public class Person {

    protected String username;
    protected String email;
    protected String password;
    protected String role;
    protected double fcoins;

    public Person(){}

    public Person(String nombre, String correo){
        username = nombre;
        email = correo;
    }

    public Person(String nombre, String correo, String clave, String rol,double coins){
        username = nombre;
        email = correo;
        password = clave;
        role = rol;
        fcoins = coins;
    }


    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", fcoins=" + fcoins +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getFcoins() {
        return fcoins;
    }

    public void setFcoins(double fcoins) {
        this.fcoins = fcoins;
    }
}
