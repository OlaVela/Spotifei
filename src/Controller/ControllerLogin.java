
package controller;

import DAO.UsuariosDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Usuarios;
import View.Login;
import View.Frame;
import Controller.ControllerMusica;

/**
 * @author Rafael
 */

public class ControllerLogin {
    private Login login;
    private ControllerMusica ControllerMusica;
    private String email;
   

    public String getEmail() { 
        return login.getEmail().getText();
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param login
     */
    public ControllerLogin(Login login) {
        this.login = login;
        this.ControllerMusica = new ControllerMusica();
    }
    
  
    public void loginUsuario(){
        ControllerMusica.setEmailatual(getEmail());
        
        Usuarios usuario = new Usuarios(null, login.getEmail().getText(), login.getSenha().getText());
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UsuariosDAO dao = new UsuariosDAO(conn);
            ResultSet res = dao.consultar(usuario);
            if(res.next()){
                Frame frame = new Frame();
                frame.setVisible(true);
                login.setVisible(false);
            } else{
                JOptionPane.showMessageDialog(login,"Login NÃO efetuado!","Aviso",JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException e){    
            JOptionPane.showMessageDialog(login,"Erro de conexão!","Aviso",JOptionPane.ERROR_MESSAGE);
        }
    }
}
