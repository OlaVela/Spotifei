package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael
 */
public class MusicasDAO {

    private Connection conn;

    public MusicasDAO(Connection conn) throws SQLException {
        this.conn = new Conexao().getConnection();
    }

    public List<String> listarMusicasTodas() {
        List<String> nomesMusicas = new ArrayList<>();
        String parametros = "SELECT nomemusica FROM musica";
        try (PreparedStatement stmt = conn.prepareStatement(parametros); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nomesMusicas.add(rs.getString("nomemusica"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar nomes das músicas", e);
        }
        return nomesMusicas;
    }

    public String MusicasDescricao(String selecionado) throws SQLException {
        String parametros = "SELECT descrição FROM musica WHERE nomemusica = ?";
        try (PreparedStatement stmt = conn.prepareStatement(parametros)) {
            stmt.setString(1, selecionado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        return rs.getString("descrição");
                    } catch (SQLException e) {
                        try {
                            return rs.getString("descrição");
                        } catch (SQLException e2) {
                            return rs.getString(1);
                        }
                    }
                }
            }
        }
        return null;
    }

    

    public List<String> pesquisarMusicas(String termo) {
        List<String> nomesMusicas = new ArrayList<>();
        String parametros = "SELECT DISTINCT nomemusica FROM musica "
                + "WHERE genero LIKE ? OR nomemusica LIKE ? OR artista LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(parametros)) {
            String termoBusca = "%" + termo + "%";
            stmt.setString(1, termoBusca); // genero
            stmt.setString(2, termoBusca); // nomemusica
            stmt.setString(3, termoBusca); // artista
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    nomesMusicas.add(rs.getString("nomemusica"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar músicas", e);
        }

        return nomesMusicas;
    }

    public void inserirMusicasExemplo() {
        String parametros = "INSERT INTO public.musica(nomemusica, artista, genero, imagem, \"descrição\") VALUES (?, ?, ?, ?, ?)";

        String[][] musicas = {
            {"Bohemian Rhapsody", "Queen", "Rock", "/View/queen.png", "Clássico do rock com múltiplas seções"},
            {"Shape of You", "Ed Sheeran", "Pop", "/View/edsheeran.png", "Hit pop internacional"},
            {"Smells Like Teen Spirit", "Nirvana", "Grunge", "/View/nirvana.png", "Hino da geração dos anos 90"},
            {"Billie Jean", "Michael Jackson", "Pop", "/View/michael.png", "Um dos maiores sucessos do Rei do Pop"},
            {"Imagine", "John Lennon", "Rock", "/View/lennon.png", "Hino pacifista de John Lennon"}
        };

        try (PreparedStatement stmt = conn.prepareStatement(parametros)) {
            for (String[] musica : musicas) {
                stmt.setString(1, musica[0]); // nome
                stmt.setString(2, musica[1]); // artista
                stmt.setString(3, musica[2]); // genero
                stmt.setString(4, musica[3]); // imagem
                stmt.setString(5, musica[4]); // descrição
                stmt.executeUpdate();
            }
            System.out.println("5 músicas inseridas com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir músicas de exemplo", e);
        }
    }
}
