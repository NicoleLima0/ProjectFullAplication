package senac.java.Domain;

import org.json.JSONObject;

public class Users {
    public static int Id = 0;
    public static String name = "";
    public static String lastName = "";
    public static String cpf = "";
    public static String email = "";


    public Users() {

    }

    public Users(int Id, String name, String lastName, String cpf, String email) {
        this.Id = Id;
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        json.put("lastName", lastName);
        json.put("cpf", cpf);
        json.put("email", email);
        return json;
    }

}


