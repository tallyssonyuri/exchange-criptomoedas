/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criptomoedas;

/**
 *
 * @author Home
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Usuario {
    private String cpf;
    private String senha;
    private double saldoReais;
    private Map<String, Moeda> moedas; // Portfólio de criptomoedas

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
        this.saldoReais = 0.0;
        this.moedas = new HashMap<>();
        // Inicializa as moedas no portfólio do usuário
        moedas.put("Bitcoin", new Bitcoin());
        moedas.put("Ethereum", new Ethereum());
        moedas.put("Ripple", new Ripple());
    }

    // Métodos para acessar e modificar as moedas
    public Moeda getMoeda(String nome) {
        return moedas.get(nome);
    }

    // Métodos para modificar o saldo
    public double getSaldoReais() {
        return saldoReais;
    }

    public void setSaldoReais(double saldo) {
        this.saldoReais = saldo;
    }

    public void adicionarSaldo(double valor) {
        this.saldoReais += valor;
    }

    public void removerSaldo(double valor) {
        if (valor <= saldoReais) {
            this.saldoReais -= valor;
        }
    }
    
    public Collection<Moeda> getTodasMoedas() {
        return moedas.values();
    }

    // Getters
    public String getCpf() { return cpf; }
    public String getSenha() { return senha; }
}