package Model;
/**
 * @author Rafael
 */
public class Usuarios extends Pessoa {

    private String  email, senha;

    /**
     *
     * @param nome
     * @param email
     * @param senha
     */
    public Usuarios(String nome,String email,String senha ) {
        super(nome);
        this.email = email;
        this.senha = senha;
    }
    
    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }
    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
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
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}