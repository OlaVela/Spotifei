package DAO;

import Model.Musicas;
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

    public List<String> listarNomesMusicas() {
        List<String> nomesMusicas = new ArrayList<>();
        String sql = "SELECT nomemusica FROM musica";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                nomesMusicas.add(rs.getString("nomemusica"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar nomes das músicas", e);
        }
        return nomesMusicas;
    }

    public void inserirMusicasExemplo() {
        String sql = "INSERT INTO public.musica(nomemusica, artista, genero, imagem, \"descrição\") VALUES (?, ?, ?, ?, ?)";

        String[][] musicas = {
            {"Bohemian Rhapsody", "Queen", "Rock", "/View/queen.png", "Clássico do rock com múltiplas seções"},
            {"Shape of You", "Ed Sheeran", "Pop", "/View/edsheeran.png", "Hit pop internacional"},
            {"Smells Like Teen Spirit", "Nirvana", "Grunge", "/View/nirvana.png", "Hino da geração dos anos 90"},
            {"Billie Jean", "Michael Jackson", "Pop", "/View/michael.png", "Um dos maiores sucessos do Rei do Pop"},
            {"Imagine", "John Lennon", "Rock", "/View/lennon.png", "Hino pacifista de John Lennon"}
        };

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
