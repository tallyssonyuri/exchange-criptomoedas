/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criptomoedas;

/**
 *
 * @author Home
 */
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = null;
        ExtratoDAO extratoDAO = null;

        try {
            // Tentativa de conexão com o banco de dados
            Conexao conexaoDB = new Conexao();
            Connection conexao = conexaoDB.getConnection();
            usuarioDAO = new UsuarioDAO(conexao);
            extratoDAO = new ExtratoDAO(conexao);

            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            // Exibe mensagem de erro e segue sem banco de dados
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            System.out.println("Executando sem conexão com o banco de dados.");
            
            // Inicializa `usuarioDAO` e `extratoDAO` com implementações alternativas
            usuarioDAO = new UsuarioDAO(null);  // Ajuste se necessário
            extratoDAO = new ExtratoDAO(null);  // Ajuste se necessário
        }

        // Inicializa a interface gráfica de login
        JanelaLogin janelaLogin = new JanelaLogin(usuarioDAO, extratoDAO);
        janelaLogin.setVisible(true);
    }
}

