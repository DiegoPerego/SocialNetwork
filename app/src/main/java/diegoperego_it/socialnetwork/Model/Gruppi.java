package diegoperego_it.socialnetwork.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by utente3.academy on 06-Dec-17.
 */

public class Gruppi implements Serializable{

    private String nome;

    private List<Post> posts;

    public Gruppi(String nome, List<Post> posts) {
        this.nome = nome;
        this.posts = posts;
    }


    public Gruppi() {
        this.nome = null;
        this.posts = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
