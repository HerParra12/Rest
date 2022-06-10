package co.edu.unbosque.rest.model;

public class Art {

    private String title;
    private String price;
    private String userApp;
    private String img;
    private String collection;
    private String owner;

    public Art(){}

    public Art(String titulo, String precio, String email, String link, String coleccion){
        title = titulo;
        price = precio;
        userApp = email;
        img = link;
        collection = coleccion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserApp() {
        return userApp;
    }

    public void setUserApp(String userApp) {
        this.userApp = userApp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
