package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.Domain.Sales;
import senac.java.Services.ResponseEndpoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SalesController {
    static ResponseEndpoints res = new ResponseEndpoints();
    private static List<Sales> salesList = new ArrayList<>();

    public static class salesHandler implements HttpHandler { // criando uma classe para modificar uma outra classe
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "";

            if ("GET".equals(exchange.getRequestMethod())) {
                response = "Essa é a rota de sales - GET";
                res.enviarResponse(exchange, response, 200);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                try (InputStream requestBody = exchange.getRequestBody()) {
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                    Sales sale = new Sales(
                            json.getString("user"),
                            json.getString("products"),
                            json.getDouble("valor"),
                            json.getBoolean("finishedSale"),
                            json.getDouble("discount"),
                            json.getString("dateSale")
                    );

                    salesList.add(sale);
                    response = "Dados recebidos com sucesso!";
                    res.enviarResponseJson(exchange, sale.toJson(), 201);

                } catch (Exception e) {
                    System.out.println("O erro foi: " + e);

                }
                res.enviarResponse(exchange, response, 200);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                response = "Essa é a rota de sales - PUT";
                res.enviarResponse(exchange, response, 200);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                response = "Essa é a rota de sales - DELETE";
                res.enviarResponse(exchange, response, 200);
            } else {
                response = "Rota sales - Método não disponivel." + " O método utilizado foi: " + exchange.getRequestMethod();
                res.enviarResponse(exchange, response, 405);
            }
        }
    }

}
