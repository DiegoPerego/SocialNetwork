package diegoperego_it.socialnetwork.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by utente3.academy on 11-Dec-17.
 */

public class Post implements Serializable {

    private String autore;
    private String contenuto;
    private Date data;
    private String titolo;

    public Post(String autore, String contenuto, Date data, String titolo) {
        this.autore = autore;
        this.contenuto = contenuto;
        this.data = data;
        this.titolo = titolo;
    }

    public Post() {
        this.autore = null;
        this.contenuto = null;
        this.data = null;
        this.titolo = null;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
