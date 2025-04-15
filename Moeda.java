package criptomoedas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home
 */
public abstract class Moeda {
    private String nome;
    private double quantidade;
    private double cotacaoAtual;
    private double taxaCompra;
    private double taxaVenda;

    public Moeda(String nome, double cotacaoAtual, double taxaCompra, double taxaVenda) {
        this.nome = nome;
        this.cotacaoAtual = cotacaoAtual;
        this.taxaCompra = taxaCompra;
        this.taxaVenda = taxaVenda;
        this.quantidade = 0.0; // Inicialmente, o usuário não possui essa moeda
    }

    // Métodos para manipulação da quantidade de moeda
    public void adicionarQuantidade(double qtd) {
        this.quantidade += qtd;
    }

    public void removerQuantidade(double qtd) {
        if (qtd <= this.quantidade) {
            this.quantidade -= qtd;
        }
    }

    public void atualizarCotacao(double variacao) {
        this.cotacaoAtual += this.cotacaoAtual * variacao;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public double getQuantidade() { return quantidade; }
    public double getCotacaoAtual() { return cotacaoAtual; }
    public double getTaxaCompra() { return taxaCompra; }
    public double getTaxaVenda() { return taxaVenda; }
}
