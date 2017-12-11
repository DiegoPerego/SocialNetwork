package diegoperego_it.socialnetwork.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by utente3.academy on 11-Dec-17.
 */

public class Comunity implements Serializable{

    private List<Gruppi> gruppi;

    public Comunity(List<Gruppi> gruppi) {
        this.gruppi = gruppi;
    }

    public Comunity() {
        this.gruppi = new ArrayList<>();
    }

    public List<Gruppi> getGruppi() {
        return gruppi;
    }

    public void setGruppi(List<Gruppi> gruppi) {
        this.gruppi = gruppi;
    }

    public Gruppi checkGruppo(String name){
        for (Gruppi group : gruppi){
            if(group.getNome().equals(name)){
                return group;
            }
        }
        return null;
    }

    public void addGroup(Gruppi group){
        gruppi.add(group);
    }

    public void updatePost(String groupName, List<Post> postList){
        for (Gruppi group: gruppi){
            if (group.getNome().equals(groupName)){
                group.setPosts(postList);
            }
        }
    }
}
