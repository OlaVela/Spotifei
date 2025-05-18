package Controller;

import DAO.MusicasDAO;
import java.sql.Connection;
import DAO.Conexao;
import java.sql.SQLException;
import java.util.List;
/**
 * @author Rafael
 */
public class ControllerMusica {
    
    private MusicasDAO musicasDAO;
    public ControllerMusica(){
        try{
        Connection conn = new Conexao().getConnection();
        this.musicasDAO = new MusicasDAO(conn);
        }catch (SQLException e) {
            throw new RuntimeException("Falha ao inicializar controller", e);
    }  
}
    public List<String> MostrarLista() {
        List<String> musicas = musicasDAO.listarNomesMusicas();
        System.out.println(musicas);
        return musicas;
    }
}