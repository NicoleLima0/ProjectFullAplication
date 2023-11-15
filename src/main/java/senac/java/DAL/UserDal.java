package senac.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDal {

    public Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=pi";
            String usuario = "SENACRJEDU/116128412023.1";
            String senha = "senac@12841";
            conexao = DriverManager.getConnection(url, usuario, senha);

            if (conexao != null) {
                return conexao;
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("O erro foi: " + e);
        } finally {
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("O erro do SQL foi: " + e);
            }
        }
        return conexao;
    }

    public int inserirUsuario(String name, String cpf, String email) throws SQLException {
        String sql = "INSERT INTO Users (name, cpf, email) VALUES(?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, cpf);
            statement.setString(3, email);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Linhas modificadas " + linhasAfetadas + "no banco de dados");
            conexao.close();

            return linhasAfetadas;

        } catch (SQLException e) {
            System.out.println("O erro na inserção de dados foi " + e);
            conexao.close();
        }
        conexao.close();
        return linhasAfetadas;
    }

    public ResultSet listarUsuario() throws SQLException {
        String sql = "SELECT * FROM Users";
        ResultSet result = null;

        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
            result = statement.executeQuery();
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
            return result;
        } catch (SQLException e) {
            System.out.println("O erro na listagem de dados foi: " + e);
        }
        return result;
    }

    public int atualizarUsuario() throws SQLException {
        String sql = "UPDATE Users SET name = ?, cpf = ?, email = ? WHERE id = ?";
        int linhasAfetadas = 0;

        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
//            statement.setString(1, name);
//            statement.setString(2, cpf);
//            statement.setString(3, email);
//            statement.setInt(4, id);

            linhasAfetadas = statement.executeUpdate();
            System.out.println("Foram modificadas: " + linhasAfetadas + "no banco de dados");

            return linhasAfetadas;


        } catch (SQLException e) {
            System.out.println("O erro na atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public void excluirUsuario() throws SQLException {
        String sql = "DELETE From Users WHERE id = ?";
        try (PreparedStatement statement = conectar().prepareStatement(sql)) {
//            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();
            System.out.println("Foram modificadas: " + linhasAfetadas + "no banco de dados");
        } catch (SQLException e) {
            System.out.println("O erro na exclusão de dados foi: " + e);
        }
    }
}
