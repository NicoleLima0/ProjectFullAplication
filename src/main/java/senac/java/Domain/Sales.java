package senac.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Sales {
    public static int Id = 0;
    public static String user = "";
    public static String products = "";
    public static double valor = 0;
    public static boolean finishedSale = false;
    public static double discount = 0;
    public static String dateSale = "";

    public Sales(String user, String products, double valor, boolean finishedSale, double discount, String dateSale) {
        this.user = user;
        this.products = products;
        this.valor = valor;
        this.finishedSale = finishedSale;
        this.discount = discount;
        this.dateSale = dateSale;
    }
    public Sales() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts() {
        this.products = products;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getFinishedSale() {
        return finishedSale;
    }

    public void setFinishedSale() {
        this.finishedSale = finishedSale;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount() {
        this.discount = discount;
    }

    public String getDateSale() {
        return dateSale;
    }

    public void setDateSale() {
        this.dateSale = dateSale;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("products", products);
        json.put("valor", valor);
        json.put("finishedSale", finishedSale);
        json.put("discount", discount);
        json.put("dateSale", dateSale);

        return json;
    }
    public static List<Sales> getAllSales(List<Sales> salesList){
        return salesList;
    }
}
