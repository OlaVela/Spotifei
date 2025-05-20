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

    public List<String> pesquisarMusicas(String termo) {
        List<String> musicas = musicasDAO.pesquisarMusicas(termo);
        System.out.println("Resultados para '" + termo + "': " + musicas);
        return musicas;
    }

    public String Descricao(String selecionado) {
        try {
            return musicasDAO.MusicasDescricao(selecionado);
        } catch (Exception e) {
            System.err.println("Erro ao obter descrição: " + e.getMessage());
            return "Não foi possível carregar a descrição";
        }
    }
}
