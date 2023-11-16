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
    static String response = "";

    public static class UserHandler implements HttpHandler { // criando uma classe para modificar uma outra classe

        public void doGet(HttpExchange exchange) throws IOException {
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
        }

        public void doPost(HttpExchange exchange) throws IOException {
            UserDal userDal = new UserDal();
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
        }

        public void doPut(HttpExchange exchange) throws IOException {
            UserDal userDal = new UserDal();

            int myId = 0;
            String name = "";
            String email = "";
            String cpf = "";

            int resp = 0;

            try (InputStream requestBody = exchange.getRequestBody()) {
                JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                myId = Integer.parseInt(json.getString("id"));
                name = json.getString("name");
                email = json.getString("email");
                cpf = json.getString("cpf");

                userDal.atualizarUsuario(myId, name, email, cpf);

                if (resp == 0) {
                    response = "Houve um problema ao atualizar os dados do usuário";
                    res.enviarResponse(exchange, response, 200);
                } else if (resp > 0) {
                    response = "Dados atualizados com sucesso!";
                    res.enviarResponse(exchange, response, 200);
                }
            } catch (Exception e) {
                System.out.println("O erro foi: " + e);
                res.enviarResponse(exchange, "Erro ao atualizar usuário", 200);
            }

//            try {
//                userDal.atualizarUsuario();
//            } catch (Exception e) {
//                System.out.println("O erro foi: " + e);
//            }
//            response = "Essa é a rota de usuário - PUT";
//            res.enviarResponse(exchange, response, 200);
        }

        public void doDelete(HttpExchange exchange) throws IOException {
            UserDal userDal = new UserDal();

            int myId = 0;
            String name = "";
            String email = "";
            String cpf = "";

            int resp = 0;

            try (InputStream requestBody = exchange.getRequestBody()) {
                JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
                myId = Integer.parseInt(json.getString("id"));

                userDal.deletarUsuario(myId);

                if (resp == 0) {
                    response = "Houve um problema ao deletar os dados do usuário";
                    res.enviarResponse(exchange, response, 200);
                } else if (resp > 0) {
                    response = "Dados deletados com sucesso!";
                    res.enviarResponse(exchange, response, 200);
                }
            } catch (Exception e) {
                System.out.println("O erro foi: " + e);
                res.enviarResponse(exchange, "Erro ao deletar usuário", 200);
            }
//            UserDal userDal = new UserDal();
//            try {
//                userDal.excluirUsuario();
//            } catch (Exception e) {
//                System.out.println("O erro foi: " + e);
//            }
//
//            response = "Essa é a rota de usuário - DELETE";
//            res.enviarResponse(exchange, response, 200);
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "";

            if ("GET".equals(exchange.getRequestMethod())) {
                doGet(exchange);

            } else if ("POST".equals(exchange.getRequestMethod())) {
                doPost(exchange);

            } else if ("PUT".equals(exchange.getRequestMethod())) {
                doPut(exchange);

            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                doDelete(exchange);

            } else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            } else {
                response = "Rota usuário - Método não disponivel." + " O método utilizado foi: " + exchange.getRequestMethod();
                res.enviarResponse(exchange, response, 405);
            }
        }
    }
}
