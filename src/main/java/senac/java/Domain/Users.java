package senac.java.Domain;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Users {
    public int Id = 0;
    public String name = "";
    public String cpf = "";
    public String email = "";


    public Users() {

    }

    public JSONObject arrayToJson(List<Users> UsersList) {
        JSONObject json = new JSONObject();

        if (!UsersList.isEmpty()) {
            var keyJson = 0;

            for (Users user : UsersList) {
                JSONObject objJson = new JSONObject();
                objJson.put("name", user.getName());
                objJson.put("cpf", user.getCpf());
                objJson.put("email", user.getEmail());

                json.put(String.valueOf(keyJson), objJson);
                keyJson++;
            }
            return json;
        } else {
            return null;
        }
    }

    public Users(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cpf", cpf);
        json.put("email", email);
        return json;
    }

    public static Users getUser(int index, List<Users> usersList) {;
        if (index >= 0 && index < usersList.size()) {
            return usersList.get(index);
        } else {
            return null;
        }
    }

    public static List<Users> getAllUsers(List<Users> usersList) {
        return usersList;
    }

}


