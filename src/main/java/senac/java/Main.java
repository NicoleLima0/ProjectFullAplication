package senac.java;

import senac.java.Services.Conexao;
import senac.java.Services.Servidor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Servidor servidor = new Servidor();
//
//        servidor.ServerSide();

        Conexao conexao = new Conexao();
        conexao.conectar();

    }
}