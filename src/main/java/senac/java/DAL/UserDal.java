package senac.java.DAL;

import senac.java.Services.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDal {
    //Inserir

    public void inserirUsuario(Connection conexao) throws SQLException {
        String sql = "INSERT INTO Users (name, cpf, email) VALUES(?, ?, ?)*";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, cpf);
            statement.setString(3, email);

            int linhasAfetadas = statement.executeUpdate();

            System.out.println("Linhas modificadas " + linhasAfetadas + "no banco de dadpos");

        } catch (SQLException e) {
            System.out.println("O erro na inserção de dados foi " + e);
        }
    }

    public void listarUsuario(Connection conexao) throws SQLException {
        String sql = "SELECT + FROM Users";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            System.out.println("Listagem dos usuários: ");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String cpf = result.getString("cpf");
                String email = result.getString("email");

                System.out.println("id" + id);
                System.out.println("name" + name);
                System.out.println("email" + email);
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("O erro na listagem de dados foi: " + e);
        }
    }

    public void atualizarUsuario(Connection conexao) throws SQLException {
        String sql = "UPDATE Users SET name = ?, cpf = ?, email = ? WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, cpf);
            statement.setString(3, email);
            statement.setInt(4, id);
        }

    }

}
