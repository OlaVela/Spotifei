
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Model.Usuarios;
/**
 * @author Rafael
 */
public class UsuariosDAO {
    private Connection conn;

    public UsuariosDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultar(Usuarios usuario) throws SQLException{
        String sql = "select * from usuario where email = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getEmail());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void inserir(Usuarios usuario) throws SQLException{
        String sql = "insert into usuario (nome, email, senha) values ('"
                      + usuario.getNome()    + "', '"
                      + usuario.getEmail() + "', '"
                      + usuario.getSenha()   + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
}

