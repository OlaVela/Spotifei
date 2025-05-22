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
    
    private static String emailAtual;

    public void setEmailatual(String email) {
        emailAtual = email;
        System.out.println(emailAtual);
    }
    
    public ControllerMusica() {
        try {
            Connection conn = new Conexao().getConnection();
            this.musicasDAO = new MusicasDAO(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao inicializar controller", e);
        }
    }

    public List<String> MostrarTodaLista() {
        List<String> musicas = musicasDAO.listarMusicasTodas();
        System.out.println(musicas);
        return musicas;
    }

    public List<String> pesquisarMusicas(String termo, String selecionado) {
        List<String> musicas = musicasDAO.pesquisarMusicas(termo, selecionado);
        System.out.println("Resultados para '" + termo + "': " + musicas);
        return musicas;
    }
    
    public String Artista(String selecionado) {
        try {
            return musicasDAO.MusicasArtista(selecionado);
        } catch (SQLException e) {
            System.err.println("Erro ao obter descrição: " + e.getMessage());
            return "Não foi possível carregar a descrição";
        }
    }

    public String Descricao(String selecionado) {
        try {
            return musicasDAO.MusicasDescricao(selecionado);
        } catch (SQLException e) {
            System.err.println("Erro ao obter descrição: " + e.getMessage());
            return "Não foi possível carregar a descrição";
        }
    }

    public String Genero(String selecionado) {
        try {
            return musicasDAO.MusicasGenero(selecionado);
        } catch (SQLException e) {
            System.err.println("Erro ao obter descrição: " + e.getMessage());
            return "Não foi possível carregar a descrição";
        }
    }
    
    public void LikesDeslikes(String nomemusica,int likes, int dislikes){
        musicasDAO.QualLike(emailAtual ,nomemusica,likes,dislikes);
    }
    
    public int likesChecar(String nomemusica){
        return musicasDAO.getLikes(emailAtual, nomemusica);
    }
    
    public int DeslikeChecar(String nomemusica){
        return musicasDAO.getDislikes(emailAtual, nomemusica);
    }
    
    public List<String> getMDescurtidas(){
        List<String> musicas = musicasDAO.getMusicasDescurtidas(emailAtual);
        return musicas;
    }    


    public List<String> getMCurtidas(){
        List<String> musicas = musicasDAO.getMusicasCurtidas(emailAtual);
        return musicas;
    }
    
    public void inserirMusicasExemplo() {
        musicasDAO.inserirMusicasExemplo();
    }
}
