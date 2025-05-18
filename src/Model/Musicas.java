package Model;
/**
 * @author Rafael
 */
public class Musicas {
    private String nomeMusica;
    private String artista;
    private String genero;
    private String imagem;
    private String descricao;
    
    // Construtor
    public Musicas(String nomeMusica, String artista, String genero, String imagem, String descricao) {
        this.nomeMusica = nomeMusica;
        this.artista = artista;
        this.genero = genero;
        this.imagem = imagem;
        this.descricao = descricao;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

}


