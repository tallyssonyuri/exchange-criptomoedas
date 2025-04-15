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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class ExtratoDAO {
    private Connection conexao;

    public ExtratoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public List<String> consultarExtrato(String cpf) throws SQLException {
        String sql = "SELECT * FROM extrato WHERE cpf = ?";  // Alterado para 'extrato'
        List<String> extratoList = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String data = rs.getString("data_transacao");  // Verifique se esse é o nome correto da coluna de data
                double valor = rs.getDouble("valor");
                extratoList.add("Data: " + data + ", Valor: " + valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Propaga a exceção para o chamador
        }
        return extratoList;
    }
    
    public void registrarTransacao(String cpf, String tipoMoeda, double valor, String tipo, double quantidade) throws SQLException {
        String sql = "INSERT INTO extrato (cpf, moeda, valor, tipo_transacao, quantidade) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, tipoMoeda);
            stmt.setDouble(3, valor);
            stmt.setString(4, tipo); 
            stmt.setDouble(5, quantidade);
            stmt.executeUpdate();
        }
    }


}
