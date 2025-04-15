/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criptomoedas;

/**
 *
 * @author Home
 */
import java.sql.SQLException;
import java.util.Random;
import java.util.List;

public class Controller {
    private UsuarioDAO usuarioDAO;
    private ExtratoDAO extratoDAO;
    private Random random;

    public Controller(UsuarioDAO usuarioDAO, ExtratoDAO extratoDAO) {
        this.usuarioDAO = usuarioDAO;
        this.extratoDAO = extratoDAO;
        this.random = new Random();
    }

    // Método para realizar login
    public Usuario realizarLogin(String cpf, String senha) {
        return usuarioDAO.loginUsuario(cpf, senha);
    }

    // Método para consultar saldo
    public String consultarSaldo(Usuario usuario) {
        return "Saldo em Reais: " + usuario.getSaldoReais();
        // Futuramente, podemos adicionar o saldo das criptomoedas do usuário
    }

    // Método para consultar extrato
    public String consultarExtrato(String cpf) throws SQLException {
        StringBuilder extrato = new StringBuilder();
        List<String> extratoList = extratoDAO.consultarExtrato(cpf);
        extratoList.forEach(entry -> extrato.append(entry).append("\n"));
        return extrato.toString();
    }

    // Método para comprar criptomoedas
    public String comprarCriptomoeda(Usuario usuario, String tipoMoeda, double valorCompra) throws SQLException {
        Moeda moeda = usuario.getMoeda(tipoMoeda);
        double taxa = moeda.getTaxaCompra();
        double qtdCompra = valorCompra / moeda.getCotacaoAtual() * (1 - taxa);

        if (usuario.getSaldoReais() >= valorCompra) {
            usuario.setSaldoReais(usuario.getSaldoReais() - valorCompra);
            moeda.adicionarQuantidade(qtdCompra);
            atualizarSaldoUsuario(usuario);
            registrarTransacao(usuario, tipoMoeda, valorCompra, "compra");
            return "Compra de " + tipoMoeda + " realizada com sucesso!";
        } else {
            return "Saldo insuficiente para compra.";
        }
    }
    // Método para vender criptomoedas
    public String venderCriptomoeda(Usuario usuario, String tipoMoeda, double valorVenda) {
        try {
            Moeda moeda = usuario.getMoeda(tipoMoeda);
            double taxa = moeda.getTaxaVenda();
            double qtdVenda = valorVenda / moeda.getCotacaoAtual();

            if (moeda.getQuantidade() >= qtdVenda) {
                moeda.removerQuantidade(qtdVenda);
                double valorRecebido = valorVenda * (1 - taxa);
                usuario.setSaldoReais(usuario.getSaldoReais() + valorRecebido);
                atualizarSaldoUsuario(usuario);  // Pode lançar SQLException
                registrarTransacao(usuario, tipoMoeda, valorVenda, "venda");  // Pode lançar SQLException
                return "Venda de " + tipoMoeda + " realizada com sucesso!";
            } else {
                return "Quantidade insuficiente para venda.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao processar a venda: " + e.getMessage();
        }
    }

    // Método para atualizar a cotação das criptomoedas
        public void atualizarCotacoes(Usuario usuario) {
            double variacao = random.nextDouble() * 0.1 - 0.05; // Variação entre -5% e +5%
            for (Moeda moeda : usuario.getTodasMoedas()) {
                moeda.atualizarCotacao(variacao);
            }
        }

    // Métodos auxiliares
    private void atualizarSaldoUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.atualizarUsuario(usuario);
    }

    private void registrarTransacao(Usuario usuario, String tipoMoeda, double valor, String tipo) throws SQLException {
        extratoDAO.registrarTransacao(usuario.getCpf(), tipoMoeda, valor, tipo);
    }
    
    
}
