package diegoperego_it.socialnetwork.Model;

/**
 * Created by utente3.academy on 06-Dec-17.
 */

public class Gruppi {

    private String nome;

    public Gruppi(String nome) {
        this.nome = nome;
    }

    public Gruppi() {
        this.nome = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
