package senac.java.Domain;

public class Products {
    public static int Id = 0;
    public static String name = "";
    public static String factory = "";
    public static int quantify = 0;

    public Products(String name, String factory, int quantify) {
    }

    public Products(int Id, String name, String factory, int quantify) {
        this.Id = Id;
        this.name = name;
        this.factory = factory;
        this.quantify = quantify;
    }

    public Products() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public int getQuantify() {
        return quantify;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }
}

