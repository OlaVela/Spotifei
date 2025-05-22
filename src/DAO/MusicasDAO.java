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

//================================================================
//                            Genero
//================================================================    

    /**
     *
     * @param selecionado
     * @return
     * @throws SQLException
     */
    public String MusicasGenero(String selecionado) throws SQLException {
        String parametros = "SELECT genero FROM musica WHERE nomemusica = ?";
        try (PreparedStatement onde = conn.prepareStatement(parametros)) {
            onde.setString(1, selecionado);
            try (ResultSet resultado = onde.executeQuery()) {
                if (resultado.next()) {
                    try {
                        return resultado.getString("descrição");
                    } catch (SQLException e) {
                        try {
                            return resultado.getString("descrição");
                        } catch (SQLException e2) {
                            return resultado.getString(1);
                        }
                    }
                }
            }
        }
        return null;
    }
//================================================================
//                            Artista
//================================================================   

    /**
     *
     * @param selecionado
     * @return
     * @throws SQLException
     */
    public String MusicasArtista(String selecionado) throws SQLException {
        String parametros = "SELECT artista FROM musica WHERE nomemusica = ?";
        try (PreparedStatement onde = conn.prepareStatement(parametros)) {
            onde.setString(1, selecionado);
            try (ResultSet resultado = onde.executeQuery()) {
                if (resultado.next()) {
                    try {
                        return resultado.getString("descrição");
                    } catch (SQLException e) {
                        try {
                            return resultado.getString("descrição");
                        } catch (SQLException e2) {
                            return resultado.getString(1);
                        }
                    }
                }
            }
        }
        return null;
    }
//================================================================
//                          Descrição
//================================================================

    /**
     *
     * @param selecionado
     * @return
     * @throws SQLException
     */
    public String MusicasDescricao(String selecionado) throws SQLException {
        String parametros = "SELECT descrição FROM musica WHERE nomemusica = ?";
        try (PreparedStatement onde = conn.prepareStatement(parametros)) {
            onde.setString(1, selecionado);
            try (ResultSet resultado = onde.executeQuery()) {
                if (resultado.next()) {
                    try {
                        return resultado.getString("descrição");
                    } catch (SQLException e) {
                        try {
                            return resultado.getString("descrição");
                        } catch (SQLException e2) {
                            return resultado.getString(1);
                        }
                    }
                }
            }
        }
        return null;
    }
//================================================================
//                       Listar todos
//================================================================

    /**
     *
     * @return
     */
    public List<String> listarMusicasTodas() {
        List<String> nomesMusicas = new ArrayList<>();
        String parametros = "SELECT nomemusica FROM musica";
        try (PreparedStatement onde = conn.prepareStatement(parametros); ResultSet resultado = onde.executeQuery()) {
            while (resultado.next()) {
                nomesMusicas.add(resultado.getString("nomemusica"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar nomes das músicas", e);
        }
        return nomesMusicas;
    }
//================================================================
//                      Listar Especifico
//================================================================    

    /**
     *
     * @param termo
     * @param selecionado
     * @return
     */
    public List<String> pesquisarMusicas(String termo, String selecionado) {
        List<String> nomesMusicas = new ArrayList<>();
        if ("Todos".equals(selecionado)) {
            String todos = "SELECT nomemusica FROM musica";
            try (PreparedStatement onde = conn.prepareStatement(todos); ResultSet resultado = onde.executeQuery()) {
                while (resultado.next()) {
                    nomesMusicas.add(resultado.getString("nomemusica"));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao pesquisar músicas", e);
            }
        }
        if ("Gênero".equals(selecionado)) {
            String Genero = "SELECT DISTINCT nomemusica FROM musica WHERE genero LIKE ?";
            try (PreparedStatement onde = conn.prepareStatement(Genero)) {
                String termoBusca = "%" + termo + "%";
                onde.setString(1, termoBusca); // genero
                try (ResultSet resultado = onde.executeQuery()) {
                    while (resultado.next()) {
                        nomesMusicas.add(resultado.getString("nomemusica"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao pesquisar músicas", e);
            }
        }
        if ("Nome".equals(selecionado)) {
            String Nome = "SELECT DISTINCT nomemusica FROM musica WHERE nomemusica LIKE ?";
            try (PreparedStatement onde = conn.prepareStatement(Nome)) {
                String termoBusca = "%" + termo + "%";
                onde.setString(1, termoBusca); // nomemusica
                try (ResultSet resultado = onde.executeQuery()) {
                    while (resultado.next()) {
                        nomesMusicas.add(resultado.getString("nomemusica"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao pesquisar músicas", e);
            }
        }
        if ("Artista".equals(selecionado)) {
            String Artista = "SELECT DISTINCT nomemusica FROM musica WHERE artista LIKE ?";
            try (PreparedStatement onde = conn.prepareStatement(Artista)) {
                String termoBusca = "%" + termo + "%";
                onde.setString(1, termoBusca);// artista
                try (ResultSet resultado = onde.executeQuery()) {
                    while (resultado.next()) {
                        nomesMusicas.add(resultado.getString("nomemusica"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao pesquisar músicas", e);
            }
        }

        return nomesMusicas;
    }
//================================================================
//                     Likes E Deslikes
//================================================================   

    /**
     *
     * @param email
     * @param nomemusica
     * @param like
     * @param dislike
     */
public void QualLike(String email, String nomemusica, int like, int dislike) {
    String consultaExistencia = "SELECT * FROM curtidas WHERE email = ? AND nomemusica = ?";
    boolean existe = false;
    try (PreparedStatement stmtConsulta = conn.prepareStatement(consultaExistencia)) {
        stmtConsulta.setString(1, email);
        stmtConsulta.setString(2, nomemusica);
        
        try (ResultSet rs = stmtConsulta.executeQuery()) {
            existe = rs.next();
        }

        if (existe == true) {
            String sqlUpdate = "UPDATE curtidas SET \"like\" = ?, \"deslike\" = ? WHERE email = ? AND nomemusica = ?";
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
                stmtUpdate.setInt(1, like);           // likes = ?
                stmtUpdate.setInt(2, dislike);        // dislikes = ?
                stmtUpdate.setString(3, email);       // WHERE email = ?
                stmtUpdate.setString(4, nomemusica);  // AND nomemusica = ?
                
                int linhasAfetadas = stmtUpdate.executeUpdate();
                if (linhasAfetadas > 0) {
                } else {
                    System.err.println("Nenhum registro foi atualizado.");
                }
            }
        } else {
            String sqlInsert = "INSERT INTO public.curtidas (email, nomemusica, \"like\", \"deslike\") VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                stmtInsert.setString(1, email);
                stmtInsert.setString(2, nomemusica);
                stmtInsert.setInt(3, like);
                stmtInsert.setInt(4, dislike);
                int linhasAfetadas = stmtInsert.executeUpdate();
                if (linhasAfetadas > 0) {
                }
            }
        }
        
    } catch (SQLException e) {
        System.err.println("Erro ao processar likes: " + e.getMessage());
    }
}
//================================================================
//                         getLikes 
//================================================================ 

    /**
     *
     * @param email
     * @param nomemusica
     * @return
     */
    public int getLikes(String email, String nomemusica) {
        String parametro = "SELECT \"like\" FROM curtidas WHERE email = ? AND nomemusica = ?";
        int likes = 0;

        try (PreparedStatement onde = conn.prepareStatement(parametro)) {
            onde.setString(1, email);
            onde.setString(2, nomemusica);

            try (ResultSet resultado = onde.executeQuery()) {
                if (resultado.next()) {
                    likes = resultado.getInt("like");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar likes: " + e.getMessage());
        }

        return likes;
    }
//================================================================
//                         getDesLikes 
//================================================================ 

    /**
     *
     * @param email
     * @param nomemusica
     * @return
     */
    public int getDislikes(String email, String nomemusica) {
        String parametro = "SELECT \"deslike\" FROM curtidas WHERE email = ? AND nomemusica = ?";
        int deslike = 0;

        try (PreparedStatement onde = conn.prepareStatement(parametro)) {
            onde.setString(1, email);
            onde.setString(2, nomemusica);

            try (ResultSet resultado = onde.executeQuery()) {
                if (resultado.next()) {
                    deslike = resultado.getInt("deslike");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dislikes: " + e.getMessage());
        }

        return deslike;
    }
//================================================================
//                   listas like deslike
//================================================================

    /**
     *
     * @param email
     * @return
     */
public List<String> getMusicasCurtidas(String email) {
    List<String> musicasCurtidas = new ArrayList<>();
    String parametro = "SELECT nomemusica FROM curtidas WHERE email = ? AND \"like\" = 1";
    
    try (PreparedStatement stmt = conn.prepareStatement(parametro)) {
        stmt.setString(1, email);
        
        try (ResultSet resultado = stmt.executeQuery()) {
            while (resultado.next()) {
                musicasCurtidas.add(resultado.getString("nomemusica"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar músicas curtidas: " + e.getMessage());
    }
    return musicasCurtidas;
}

    /**
     *
     * @param email
     * @return
     */
    public List<String> getMusicasDescurtidas(String email) {
    List<String> musicasDescurtidas = new ArrayList<>();
    String parametro = "SELECT nomemusica FROM curtidas WHERE email = ? AND deslike = 1";
    
    try (PreparedStatement stmt = conn.prepareStatement(parametro)) {
        stmt.setString(1, email);
        
        try (ResultSet resultado = stmt.executeQuery()) {
            while (resultado.next()) {
                musicasDescurtidas.add(resultado.getString("nomemusica"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar músicas descurtidas: " + e.getMessage());
    }
    return musicasDescurtidas;
}
//================================================================
//                      Criar Playlist
//================================================================

    /**
     *
     * @param email
     * @param nomePlaylist
     * @return
     */
    public boolean criarPlaylist(String email, String nomePlaylist) {
    String parametro = "INSERT INTO playlist (email, nomeplaylist, musica) VALUES (?, ?, '')";
    
    try (PreparedStatement resultado = conn.prepareStatement( parametro)) {
        resultado.setString(1, email);
        resultado.setString(2, nomePlaylist);
        
        int linhasAfetadas = resultado.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao criar playlist: " + e.getMessage());
        return false;
    }
}
//================================================================
//                   Adicionar a Playlist
//================================================================

    /**
     *
     * @param email
     * @param nomePlaylist
     * @param nomemusica
     * @return
     */
    public boolean adicionarMusicaPlaylist(String email, String nomePlaylist, String nomemusica) {
    String parametro = "INSERT INTO playlist (email, nomeplaylist, musica) VALUES (?, ?, ?)";
    
    try (PreparedStatement onde = conn.prepareStatement(parametro)) {
        onde.setString(1, email);
        onde.setString(2, nomePlaylist);
        onde.setString(3, nomemusica);
        
        int linhasAfetadas = onde.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao adicionar música: " + e.getMessage());
        return false;
    }
}
//================================================================
//                    Remover da Playlist
//================================================================

    /**
     *
     * @param email
     * @param nomePlaylist
     * @param musica
     * @return
     */
    public boolean removerMusicaPlaylist(String email, String nomePlaylist, String musica) {
    String parametro = "DELETE FROM playlist WHERE email = ? AND nomeplaylist = ? AND musica = ?";
    
    try (PreparedStatement onde = conn.prepareStatement(parametro)) {
        onde.setString(1, email);
        onde.setString(2, nomePlaylist);
        onde.setString(3, musica);
        
        int linhasAfetadas = onde.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao remover música: " + e.getMessage());
        return false;
    }
}

//================================================================
//                  Listar todas as Playlist
//================================================================

    /**
     *
     * @param email
     * @return
     */
public List<String> listarPlaylistsUsuario(String email) {
    List<String> playlists = new ArrayList<>();
    String parametro = "SELECT DISTINCT nomeplaylist FROM playlist WHERE email = ?";
    
    try (PreparedStatement onde = conn.prepareStatement(parametro)) {
        onde.setString(1, email);
        
        try (ResultSet Resultado = onde.executeQuery()) {
            while (Resultado.next()) {
                playlists.add(Resultado.getString("nomeplaylist"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar playlists: " + e.getMessage());
    }
    return playlists;
}
//================================================================
//                Listar Musicas da Playlist
//================================================================

    /**
     *
     * @param email
     * @param nomePlaylist
     * @return
     */
public List<String> listarMusicasPlaylist(String email, String nomePlaylist) {
    List<String> musicas = new ArrayList<>();
    String parametro = "SELECT musica FROM playlist WHERE email = ? AND nomeplaylist = ? AND musica != ''";
    
    try (PreparedStatement onde = conn.prepareStatement(parametro)) {
        onde.setString(1, email);
        onde.setString(2, nomePlaylist);
        
        try (ResultSet Resultado = onde.executeQuery()) {
            while (Resultado.next()) {
                musicas.add(Resultado.getString("musica"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao listar músicas: " + e.getMessage());
    }
    
    return musicas;
}
//================================================================
//                   Renomear Playlist
//================================================================

    /**
     *
     * @param email
     * @param nomeAtual
     * @param novoNome
     * @return
     */
    public boolean renomearPlaylist(String email, String nomeAtual, String novoNome) {
        String sqlVerifica = "SELECT COUNT(*) FROM playlist WHERE email = ? AND nomeplaylist = ?";
        String sqlAtualiza = "UPDATE playlist SET nomeplaylist = ? WHERE email = ? AND nomeplaylist = ?";
        try (PreparedStatement stmtVerifica = conn.prepareStatement(sqlVerifica);
             PreparedStatement stmtAtualiza = conn.prepareStatement(sqlAtualiza)) {  
            stmtVerifica.setString(1, email);
            stmtVerifica.setString(2, novoNome);
            ResultSet rs = stmtVerifica.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.err.println("Já existe uma playlist com esse nome!");
                return false;
            }
            stmtAtualiza.setString(1, novoNome);
            stmtAtualiza.setString(2, email);
            stmtAtualiza.setString(3, nomeAtual);
            int linhasAfetadas = stmtAtualiza.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao renomear playlist: " + e.getMessage());
            return false;
        }
    }
//================================================================
//                     Remover Playlist
//================================================================    

    /**
     *
     * @param email
     * @param nomePlaylist
     */
    public void deletarPlaylist(String email, String nomePlaylist) {
        String parametro = "DELETE FROM playlist WHERE email = ? AND nomeplaylist = ?";
        try (PreparedStatement onde = conn.prepareStatement(parametro)) {

            onde.setString(1, email);
            onde.setString(2, nomePlaylist);

            int linhasAfetadas = onde.executeUpdate();

            if (linhasAfetadas > 0) {
            } else {
                System.err.println("Playlist não encontrada ou vazia");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar playlist: " + e.getMessage());
        }
    }
//================================================================
//                  Criar Historico de Musica
//================================================================ 
    
    /**
     *
     * @param email
     * @param musica
     */
    public void adicionarMusicaAoHistorico(String email, String musica) {
    String parametroBusca = "SELECT ultima FROM ultimasmusicas WHERE email = ?";
    String parametroInsere = "INSERT INTO ultimasmusicas (email, ultima, m1) VALUES (?, 1, ?)";
    
    try (PreparedStatement ondeBusca = conn.prepareStatement(parametroBusca);
         PreparedStatement ondeInsere = conn.prepareStatement(parametroInsere);){
        
        ondeBusca.setString(1, email);
        
        try (ResultSet resultado = ondeBusca.executeQuery()) {
            if (resultado.next()) {
                int ultimaPosicao = resultado.getInt("ultima");
                int proximaPosicao = (ultimaPosicao % 10) + 1;
                
                String parametroAtualiza = "UPDATE ultimasmusicas SET ultima = " + proximaPosicao + 
                                                         ", m" + proximaPosicao + " = ? WHERE email = ?";   
                
                try (PreparedStatement ondeAtualiza = conn.prepareStatement(parametroAtualiza)){
                    ondeAtualiza.setString(1, musica);
                    ondeAtualiza.setString(2, email);
                    ondeAtualiza.executeUpdate();
                }                

            } else {
                ondeInsere.setString(1, email);
                ondeInsere.setString(2, musica);
                ondeInsere.executeUpdate();
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar histórico: " + e.getMessage());
    }
}    
//================================================================
//                  Listar Historico de Musica
//================================================================  

    /**
     *
     * @param email
     * @return
     */
    public List<String> listarHistoricoMusicas(String email) {
    List<String> historico = new ArrayList<>();
    String parametro = "SELECT ultima, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10 FROM ultimasmusicas WHERE email = ?";
    
    try (PreparedStatement onde = conn.prepareStatement(parametro)) {  
        onde.setString(1, email);
        try (ResultSet resultado = onde.executeQuery()) {
            if (resultado.next()) {
                int ultimaPosicao = resultado.getInt("ultima");
                for (int i = 0; i < 10; i++) {
                    int posicao = (ultimaPosicao - i + 9) % 10 + 1;
                    String musica = resultado.getString("m" + posicao);
                    if (musica != null && !musica.isEmpty()) {
                        historico.add(musica);
                    }
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao buscar histórico: " + e.getMessage());
    }
    return historico;
}
//================================================================
//                Inserir todas as musicas
//================================================================ 

    public void inserirMusicasExemplo() {
        String parametros = "INSERT INTO public.musica(nomemusica, artista, genero, \"descrição\") VALUES (?, ?, ?, ?)";
        String[][] musicas = {
            {"Bohemian Rhapsody", "Queen", "Rock", "Clássico do rock com múltiplas seções"},
            {"Shape of You", "Ed Sheeran", "Pop", "Hit pop internacional"},
            {"Smells Like Teen Spirit", "Nirvana", "Grunge", "Hino da geração dos anos 90"},
            {"Billie Jean", "Michael Jackson", "Pop", "Um dos maiores sucessos do Rei do Pop"},
            {"Imagine", "John Lennon", "Rock", "Hino pacifista de John Lennon"},
            {"Sweet Child O'Mine", "Guns N' Roses", "Rock", "Riff icônico de guitarra"},
            {"Rolling in the Deep", "Adele", "Pop", "Powerhouse vocal de Adele"},
            {"Uptown Funk", "Mark Ronson ft. Bruno Mars", "Funk", "Sucesso dançante de 2014"},
            {"Hotel California", "Eagles", "Rock", "Clássico do rock com solo memorável"},
            {"Blinding Lights", "The Weeknd", "Pop", "Hit retro-pop dos anos 2020"},
            {"Wonderwall", "Oasis", "Britpop", "Hino dos anos 90"},
            {"Despacito", "Luis Fonsi ft. Daddy Yankee", "Reggaeton", "Fenômeno global em espanhol"},
            {"Like a Rolling Stone", "Bob Dylan", "Folk Rock", "Revolucionou a música popular"},
            {"Thriller", "Michael Jackson", "Pop", "Clássico do Halloween"},
            {"Poker Face", "Lady Gaga", "Pop", "Primeiro grande hit de Gaga"},
            {"Stairway to Heaven", "Led Zeppelin", "Rock", "Considerada uma das melhores músicas de rock"},
            {"Boombastic", "Shaggy", "Reggae", "Sucesso dancehall dos anos 90"},
            {"Sultans of Swing", "Dire Straits", "Rock", "Solo de guitarra lendário"},
            {"Bad Guy", "Billie Eilish", "Electropop", "Hit sombrio e minimalista"},
            {"Watermelon Sugar", "Harry Styles", "Pop", "Canção de verão vencedora do Grammy"},
            {"Don't Stop Believin'", "Journey", "Rock", "Hino atemporal de perseverança"},
            {"Levitating", "Dua Lipa", "Disco", "Sucesso pop com influência dos anos 70"},
            {"Smooth", "Santana ft. Rob Thomas", "Rock Latino", "Colaboração vencedora do Grammy"},
            {"Take On Me", "a-ha", "Synthpop", "Clássico dos anos 80 com videoclipe inovador"},
            {"Believer", "Imagine Dragons", "Rock Alternativo", "Hit de estádio energético"}
        };

        try (PreparedStatement onde = conn.prepareStatement(parametros)) {
            for (String[] musica : musicas) {
                onde.setString(1, musica[0]); // nome
                onde.setString(2, musica[1]); // artista
                onde.setString(3, musica[2]); // genero
                onde.setString(4, musica[3]); // descrição
                onde.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir músicas de exemplo", e);
        }
    }
}
