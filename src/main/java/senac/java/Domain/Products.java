package senac.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Products {
    public static int Id = 0;
    public static String name = "";
    public static String factory = "";
    public static int quantify = 0;
    public JSONObject toJson;



    public Products(String name, String factory, int quantify) {
        this.name = name;
        this.factory = factory;
        this.quantify = quantify;
    }

    public Products() {

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

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("factory", factory);
        json.put("quantify", quantify);

        return json;
    }
    public static List<Products> getAllProducts(List<Products> productsList){
        return productsList;
    }
}

