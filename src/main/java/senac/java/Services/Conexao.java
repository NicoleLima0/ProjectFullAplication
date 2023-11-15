package senac.java.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=pi";
            String usuario = "SENACRJEDU/116128412023.1";
            String senha = "senac@12841";
            conexao = DriverManager.getConnection(url, usuario, senha);

            if (conexao != null) {
                System.out.println("Conexão com a base de dados concluída com sucesso!");
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
}
