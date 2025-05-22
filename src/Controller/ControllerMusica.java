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
    }
    
    public ControllerMusica() {
        try {
            Connection conn = new Conexao().getConnection();
            this.musicasDAO = new MusicasDAO(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao inicializar controller", e);
        }
    }
//================================================================
//                        Listas Pesquisas
//================================================================
    public List<String> MostrarTodaLista() {
        List<String> musicas = musicasDAO.listarMusicasTodas();
        return musicas;
    }

    public List<String> pesquisarMusicas(String termo, String selecionado) {
        List<String> musicas = musicasDAO.pesquisarMusicas(termo, selecionado);
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
//================================================================
//                     Likes e Deslikes
//================================================================    
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
    
//================================================================
//                       Playlistis
//================================================================
    
    public void criarPlaylist(String nomePlaylist){
    musicasDAO.criarPlaylist(emailAtual,nomePlaylist);
    }
    
    public void adicionarMusicaPlaylist(String nomePlaylist, String nomemusica){       
    musicasDAO.adicionarMusicaPlaylist(emailAtual, nomePlaylist, nomemusica);
    }
    
    public void removerMusicaPlaylist( String nomePlaylist, String musica){
    musicasDAO.removerMusicaPlaylist(emailAtual, nomePlaylist, musica);
    }

    public void removerMusicaPlaylist(String email, String nomePlaylist, String musica){
        musicasDAO.removerMusicaPlaylist(emailAtual, nomePlaylist, musica); 
    }
    
    public List<String> getListaPlaylists(){
         List<String> musicas = musicasDAO.listarPlaylistsUsuario(emailAtual);
         return musicas;
    }
    
    public List<String> getListaMusicasPlaylist(String nomePlaylist){
        List<String> musicas = musicasDAO.listarMusicasPlaylist(emailAtual,nomePlaylist);
        return musicas;
    }
    
    public void renomearPlaylist(String nomeAtual, String novoNome){
        musicasDAO.renomearPlaylist(emailAtual, nomeAtual, novoNome);
    }
    
    public void deletarPlaylist(String nomePlaylist){
        musicasDAO.deletarPlaylist(emailAtual, nomePlaylist);
    }
    public void adicionarMusicaAoHistorico(String musica){
        musicasDAO.adicionarMusicaAoHistorico(emailAtual, musica);
    }
    
    public List<String> listarHistoricoMusicas(){
        List<String> musicas = musicasDAO.listarHistoricoMusicas(emailAtual);
        return musicas;
    }
    
//================================================================
//                       Inserir Teste
//================================================================    
    
    public void inserirMusicasExemplo() {
        musicasDAO.inserirMusicasExemplo();
    }
}
