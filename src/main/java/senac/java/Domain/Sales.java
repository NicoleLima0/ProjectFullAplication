package senac.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Sales {
    int Id;
    double qntd = 0;
    double price = 0;
    String name = "";
    String categoria = "";
    String dataPedido = "true";

    public Sales() {
    }

    public Sales(double qntd, double price, String name, String categoria, String dataPedido) {
        this.qntd = qntd;
        this.price = price;
        this.name = name;
        this.dataPedido = dataPedido;
        this.categoria = categoria;
    }

    public double getQntd() {
        return qntd;
    }

    public void setQntd(double qntd) {
        this.qntd = qntd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public JSONObject tojson() {
        JSONObject json = new JSONObject();
        json.put("qntd", qntd);
        json.put("price", price);
        json.put("name", name);
        json.put("categoria", categoria);
        json.put("dataPedido", dataPedido);
        return json;
    }

    public JSONObject arrayToJson(List<Sales> salesList) {
        JSONObject json = new JSONObject();
        if (!salesList.isEmpty()) {
            var keyJson = 0;
            for (Sales sales : salesList) {
                JSONObject jsonFor = new JSONObject();

                jsonFor.put("qntd", sales.getQntd());
                jsonFor.put("price", sales.getPrice());
                jsonFor.put("name", sales.getName());
                jsonFor.put("categoria", sales.getCategoria());
                jsonFor.put("dataPedido", sales.getDataPedido());
                keyJson++;
                json.put(String.valueOf(keyJson), jsonFor);
            }
            return json;
        } else {
            return null;
        }
    }

    public static List<Sales> getAllSales(List<Sales> salesList) {
        return salesList;
    }

    public static Sales getSales(int index, List<Sales> salesList) {
        if (index >= 0 && index < salesList.size()) {
            return salesList.get(index);
        } else {
            return null;
        }
    }
}