package senac.java.Services;

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

        HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);//preciso criar um servidor http

        server.createContext("/api/usuario", new UserController.UserHandler());
        server.createContext("/api/produto", new ProductsController.ProductsHandler());
        server.createContext("/api/vendas", new SalesController.salesHandler());

        server.setExecutor(null); //configurações do servidor
        server.start();
        System.out.println("Servidor Iniciado!");

    }
}
