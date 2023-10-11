package senac.java.Domain;

public class Sales {
    public static int Id = 0;
    public static String user = "";
    public static String products = "";
    public static double valor = 0;
    public static boolean finishedSale = false;
    public static double discount = 0;
    public static String dateSale = "";

    public Sales(String user, String products, double valor, boolean finishedSale, double discount, String dateSale) {
    }

    public Sales(int Id, String user, String products, double valor, boolean finishedSale, double discount, String dateSale) {
        this.Id = Id;
        this.user = user;
        this.products = products;
        this.valor = valor;
        this.finishedSale = finishedSale;
        this.discount = discount;
        this.dateSale = dateSale;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getProducts(){
        return products;
    }
    public void setProducts(){
        this.products = products;
    }
    public double getValor(){
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public boolean getFinishedSale(){
        return finishedSale;
    }
    public void setFinishedSale(){
        this.finishedSale = finishedSale;
    }
    public double getDiscount(){
        return discount;
    }
    public void setDiscount(){
        this.discount = discount;
    }
    public String getDateSale(){
        return dateSale;
    }
    public void setDateSale(){
        this.dateSale = dateSale;
    }
}
