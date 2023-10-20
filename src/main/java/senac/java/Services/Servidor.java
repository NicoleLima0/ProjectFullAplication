package senac.java.Services;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import senac.java.Controllers.ProductsController;
import senac.java.Controllers.SalesController;
import senac.java.Controllers.UserController;
import senac.java.Domain.Products;
import senac.java.Domain.Users;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Servidor {
    //     static HttpExchange ex;
    public void ServerSide() throws IOException {
        Products product = new Products();
        Users users = new Users();

        HttpServer server = HttpServer.create(new InetSocketAddress(4000), 0);//preciso criar um servidor http

        HttpHandler userHandler = new UserController.UserHandler();
        HttpHandler salesHandler = new SalesController.salesHandler();
        HttpHandler productsHandler = new ProductsController.ProductsHandler();


        server.createContext("/api/usuario", exchange -> {
            configureCorsHeaders(exchange);
            userHandler.handle(exchange);
        });

        server.createContext("/api/produto", exchange -> {
            configureCorsHeaders(exchange);
            productsHandler.handle(exchange);
        });

        server.createContext("/api/vendas", exchange -> {
            configureCorsHeaders(exchange);
            salesHandler.handle(exchange);
        });

        server.setExecutor(null); //configurações do servidor
        server.start();
        System.out.println("Servidor Iniciado!");

    }

    private void configureCorsHeaders(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
        headers.set("Access-Control-Allow-Headers", "Content-Type");
    }
}
