package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.Domain.Users;
import senac.java.Services.ResponseEndpoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    static ResponseEndpoints res = new ResponseEndpoints();
    private static List<Users> usersList = new ArrayList<>();

    public static class UserHandler implements HttpHandler { // criando uma classe para modificar uma outra classe
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "";

            if ("GET".equals(exchange.getRequestMethod())) {
                List<Users> getAllArray = Users.getAllUsers(usersList);
                Users userJson = new Users();
                if (!getAllArray.isEmpty()) {
                    for (Users user : getAllArray) {
                        System.out.println("name: " + user.getName());
                        System.out.println(("lastName: " + user.getLastName()));
                        System.out.println(("cpf: " + user.getCpf()));
                        System.out.println(("email: " + user.getEmail()));
                    }
                    res.enviarResponseJson(exchange, userJson.arrayToJson(getAllArray), 200);

                } else {
                    System.out.println("Nenhum usuário encontrado!");
                    response = "Dados encontrados com sucesso!";
                    res.enviarResponse(exchange, response, 200);
                }

            } else if ("POST".equals(exchange.getRequestMethod())) {
                try (InputStream requestBody = exchange.getRequestBody()) {
                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                    Users user = new Users(
                            json.getString("name"),
                            json.getString("lastName"),
                            json.getString("cpf"),
                            json.getString("email")
                    );

                    usersList.add(user);

                    System.out.println("UserList contém " + user.toJson());
                    response = "Dados recebidos com sucesso!";

                    res.enviarResponseJson(exchange, user.toJson(), 201);

                } catch (Exception e) {
                    System.out.println("O erro foi: " + e);

                }
                res.enviarResponse(exchange, response, 200);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                response = "Essa é a rota de usuário - PUT";
                res.enviarResponse(exchange, response, 200);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                response = "Essa é a rota de usuário - DELETE";
                res.enviarResponse(exchange, response, 200);
            } else {
                response = "Rota usuário - Método não disponivel." + " O método utilizado foi: " + exchange.getRequestMethod();
                res.enviarResponse(exchange, response, 405);
            }
        }
    }

}
