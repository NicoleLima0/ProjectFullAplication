package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.Domain.Products;
import senac.java.Domain.Users;
import senac.java.Services.ResponseEndpoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import senac.java.Domain.Products;

public class ProductsController {
    static ResponseEndpoints res = new ResponseEndpoints();
    private static List<Products> productsList = new ArrayList<>();

    public static class ProductsHandler implements HttpHandler { // criando uma classe para modificar uma outra classe
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "";

            if ("GET".equals(exchange.getRequestMethod())) {
                List<Products> getAllArray = Products.getAllProducts(productsList);
                if (!getAllArray.isEmpty()) {
                    for (Products product : getAllArray) {
                        System.out.println("name: " + product.getName());
                        System.out.println(("factory: " + product.getFactory()));
                        System.out.println(("quantify: " + product.getQuantify()));
                    }
                    response = "Dados encontrados com sucesso!";
                    res.enviarResponse(exchange, response, 200);
                } else if ("POST".equals(exchange.getRequestMethod())) {
                    try (InputStream requestBody = exchange.getRequestBody()) {
                        JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                        Products product = new Products(
                                json.getString("name"),
                                json.getString("factory"),
                                json.getInt("quantify")
                        );

                        productsList.add(product);
                        response = "Dados recebidos com sucesso!";
                        res.enviarResponseJson(exchange, product.toJson(), 201);

                    } catch (Exception e) {
                        System.out.println("O erro foi: " + e);

                    }
                } else if ("PUT".equals(exchange.getRequestMethod())) {
                    response = "Essa é a rota de products - PUT";
                    res.enviarResponse(exchange, response, 200);
                } else if ("DELETE".equals(exchange.getRequestMethod())) {
                    response = "Essa é a rota de products - DELETE";
                    res.enviarResponse(exchange, response, 200);
                } else {
                    response = "Rota products - Método não disponivel." + " O método utilizado foi: " + exchange.getRequestMethod();
                    res.enviarResponse(exchange, response, 405);
                }
            }
        }
    }
}

