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

public class JanelaLogin extends JFrame {
    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private final UsuarioDAO usuarioDAO;
    private final ExtratoDAO extratoDAO;

    public JanelaLogin(UsuarioDAO usuarioDAO, ExtratoDAO extratoDAO) {
        this.usuarioDAO = usuarioDAO;
        this.extratoDAO = extratoDAO;

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configura layout e componentes
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(10, 20, 80, 25);
        panel.add(labelCpf);

        txtCpf = new JTextField(20);
        txtCpf.setBounds(100, 20, 165, 25);
        panel.add(txtCpf);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(10, 50, 80, 25);
        panel.add(labelSenha);

        txtSenha = new JPasswordField(20);
        txtSenha.setBounds(100, 50, 165, 25);
        panel.add(txtSenha);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(10, 80, 80, 25);
        panel.add(btnLogin);
    }

    private void realizarLogin() {
        String cpf = txtCpf.getText();
        String senha = new String(txtSenha.getPassword());

        Usuario usuario = usuarioDAO.loginUsuario(cpf, senha);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
            // Instancie o Controller e passe-o para a JanelaPrincipal
            Controller controller = new Controller(usuarioDAO, extratoDAO);
            JanelaPrincipal janelaPrincipal = new JanelaPrincipal(usuario, controller);
            janelaPrincipal.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou senha incorretos.");
        }
    }
}