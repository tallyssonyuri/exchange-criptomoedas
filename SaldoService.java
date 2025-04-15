/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criptomoedas;

/**
 *
 * @author Home
 */
public class SaldoService {
    private UsuarioDAO usuarioDAO;

    public SaldoService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void consultarSaldo(Usuario usuario) {
        System.out.println("Saldo em Reais: " + usuario.getSaldoReais());
        // Futuramente, incluir saldo de outras moedas como Bitcoin, Ethereum e Ripple
    }
}
