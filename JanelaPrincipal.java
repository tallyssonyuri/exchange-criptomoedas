/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criptomoedas;

/**
 *
 * @author Home
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class JanelaPrincipal extends JFrame {
    private final Usuario usuario;
    private final Controller controller;
    private final JButton btnConsultarSaldo;
    private final JButton btnConsultarExtrato;
    private final JButton btnComprar;
    private final JButton btnVender;
    private final JButton btnAtualizarCotacoes;
    private final JTextField txtValorTransacao;
    private final JComboBox<String> cmbMoedas;
    private final JTextArea txtAreaExtrato;
    private final JTextArea txtAreaCotacoes;

    public JanelaPrincipal(Usuario usuario, Controller controller) {
        this.usuario = usuario;
        this.controller = controller;

        setTitle("Painel Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        // Botão de Consultar Saldo
        btnConsultarSaldo = new JButton("Consultar Saldo");
        btnConsultarSaldo.setBounds(10, 20, 150, 25);
        panel.add(btnConsultarSaldo);

        // Botão de Consultar Extrato
        btnConsultarExtrato = new JButton("Consultar Extrato");
        btnConsultarExtrato.setBounds(10, 50, 150, 25);
        panel.add(btnConsultarExtrato);

        // Campo de valor para transações
        JLabel lblValorTransacao = new JLabel("Valor:");
        lblValorTransacao.setBounds(10, 80, 150, 25);
        panel.add(lblValorTransacao);

        txtValorTransacao = new JTextField(20);
        txtValorTransacao.setBounds(60, 80, 100, 25);
        panel.add(txtValorTransacao);

        // ComboBox para selecionar a moeda
        cmbMoedas = new JComboBox<>(new String[] {"Bitcoin", "Ethereum", "Ripple"});
        cmbMoedas.setBounds(180, 80, 100, 25);
        panel.add(cmbMoedas);

        // Botão de Compra
        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(10, 110, 100, 25);
        panel.add(btnComprar);

        // Botão de Venda
        btnVender = new JButton("Vender");
        btnVender.setBounds(120, 110, 100, 25);
        panel.add(btnVender);

        // Botão de Atualizar Cotações
        btnAtualizarCotacoes = new JButton("Atualizar Cotações");
        btnAtualizarCotacoes.setBounds(10, 140, 210, 25);
        panel.add(btnAtualizarCotacoes);

        // Área para exibir cotações e saldo
        txtAreaCotacoes = new JTextArea();
        txtAreaCotacoes.setBounds(10, 180, 460, 80);
        txtAreaCotacoes.setEditable(false);
        panel.add(txtAreaCotacoes);

        // Área para exibir extrato
        txtAreaExtrato = new JTextArea();
        txtAreaExtrato.setBounds(10, 270, 460, 80);
        txtAreaExtrato.setEditable(false);
        panel.add(txtAreaExtrato);

        // Ações dos botões
    btnConsultarExtrato.addActionListener(e -> {
        try {
            consultarExtrato();  // Chama o método que pode lançar SQLException
        } catch (SQLException ex) {
            ex.printStackTrace();  
        }
    });

    btnComprar.addActionListener(e -> {
        try {
            realizarCompra();  
        } catch (SQLException ex) {
            ex.printStackTrace();  
        }
    });
    btnConsultarSaldo.addActionListener(e -> consultarSaldo());
    btnVender.addActionListener(e -> realizarVenda());
    btnAtualizarCotacoes.addActionListener(e -> atualizarCotacoes());
    }

    private void consultarSaldo() {
        String saldoInfo = controller.consultarSaldo(usuario);
        txtAreaCotacoes.setText(saldoInfo);
    }

    private void consultarExtrato() throws SQLException {
        String extrato = controller.consultarExtrato(usuario.getCpf());
        txtAreaExtrato.setText(extrato);
    }

    private void realizarCompra() throws SQLException {
        String moeda = (String) cmbMoedas.getSelectedItem();
        double valorCompra = Double.parseDouble(txtValorTransacao.getText());
        String resultado = controller.comprarCriptomoeda(usuario, moeda, valorCompra);
        txtAreaCotacoes.setText(resultado);
        consultarSaldo();
    }

    private void realizarVenda() {
        String moeda = (String) cmbMoedas.getSelectedItem();
        double valorVenda = Double.parseDouble(txtValorTransacao.getText());
        String resultado = controller.venderCriptomoeda(usuario, moeda, valorVenda);
        txtAreaCotacoes.setText(resultado);
        consultarSaldo();
    }

    private void atualizarCotacoes() {
        controller.atualizarCotacoes(usuario);  
        txtAreaCotacoes.setText("Cotações atualizadas:\n");
        for (Moeda moeda : usuario.getTodasMoedas()) {
            txtAreaCotacoes.append(moeda.getNome() + ": " + moeda.getCotacaoAtual() + "\n");
        }
    }
}
