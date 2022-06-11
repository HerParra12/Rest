package co.edu.unbosque.restsql.model;

public class Artist extends Person{

    private String description;

    public Artist(){}

    public Artist(String nombre, String correo, String clave, String role, String descripcion) {
        super(nombre, correo, clave, role,0);
        description = descripcion;
    }

    @Override
    public String toString() {
        return super.toString();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
