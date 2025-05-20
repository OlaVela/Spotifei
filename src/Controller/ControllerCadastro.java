package controller;

import DAO.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import View.*;
import Model.*;

/**
 * @author Rafael
 */
public class ControllerCadastro {

    private Cadastro cadastro;

    public ControllerCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public void salvarUsuario() {
        String nome = cadastro.getNome().getText();
        String email = cadastro.getEmail().getText();
        String senha = cadastro.getSenha().getText();
        Usuarios usuario = new Usuarios(nome, email, senha);

        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            UsuariosDAO dao = new UsuariosDAO(conn);
            dao.inserir(usuario);
            JOptionPane.showMessageDialog(cadastro, "Foi cadastrado!", "Concluido", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(cadastro, "Usuário não foi cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
