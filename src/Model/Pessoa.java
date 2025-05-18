package Model;
/**
 * @author Rafael
 */
public abstract class Pessoa {
    
    String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Pessoa(String nome){
        this.nome = nome;
    };
}
