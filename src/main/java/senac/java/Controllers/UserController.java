package senac.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import senac.java.DAL.UserDal;
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
                UserDal userDal = new UserDal();


                List<Users> getAllArray = Users.getAllUsers(usersList);
                Users userJson = new Users();
                if (!getAllArray.isEmpty()) {
                    for (Users user : getAllArray) {
                        System.out.println("name: " + user.getName());
                        System.out.println(("cpf: " + user.getCpf()));
                        System.out.println(("email: " + user.getEmail()));
                    }

                    try {
                        userDal.listarUsuario();
                    } catch (Exception e) {
                        System.out.println("O erro foi:" + e);
                    }
                    res.enviarResponseJson(exchange, userJson.arrayToJson(getAllArray), 200);

                } else {
                    System.out.println("Nenhum usuário encontrado!");
                    response = "Dados encontrados com sucesso!";
                    res.enviarResponse(exchange, response, 200);

                }
                if ("POST".equals(exchange.getRequestMethod())) {
                    try (InputStream requestBody = exchange.getRequestBody()) {
                        JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                        int resp = 0;

                        Users user = new Users(
                                json.getString("name"),
                                json.getString("cpf"),
                                json.getString("email")
                        );

                        usersList.add(user);
                        resp = userDal.inserirUsuario(user.name, user.cpf, user.email);

                        if (resp == 0) {
                            response = "Houve um problema";
                        } else {
                            response = "Usuário criado com sucesso!";
                        }

                        System.out.println("UserList contém " + user.toJson());
                        response = "Dados recebidos com sucesso!";

                        res.enviarResponseJson(exchange, user.toJson(), 201);

                    } catch (Exception e) {
                        System.out.println("Cheguei no catch do POST");
                        response = e.toString();

                        System.out.println(response);
                        res.enviarResponse(exchange, "Bad Request", 200);

                    }
                } else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                    exchange.sendResponseHeaders(204, -1);
                    exchange.close();
                    return;
                }
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                UserDal userDal = new UserDal();
                try {
                    userDal.atualizarUsuario();
                } catch (Exception e) {
                    System.out.println("O erro foi: " + e);
                }
                response = "Essa é a rota de usuário - PUT";
                res.enviarResponse(exchange, response, 200);

            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                UserDal userDal = new UserDal();
                try {
                    userDal.excluirUsuario();
                } catch (Exception e) {
                    System.out.println("O erro foi: " + e);
                }

                response = "Essa é a rota de usuário - DELETE";
                res.enviarResponse(exchange, response, 200);

            } else {
                response = "Rota usuário - Método não disponivel." + " O método utilizado foi: " + exchange.getRequestMethod();
                res.enviarResponse(exchange, response, 405);
            }
        }
    }
}
