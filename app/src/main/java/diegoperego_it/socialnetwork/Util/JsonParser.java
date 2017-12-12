package diegoperego_it.socialnetwork.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import diegoperego_it.socialnetwork.Model.Gruppi;
import diegoperego_it.socialnetwork.Model.Post;

/**
 * Created by utente3.academy on 06-Dec-17.
 */

public class JsonParser {

    public static List<Gruppi> findGroup(String json){
        List<Gruppi>gruppi = new ArrayList<>();
        String getKey = null;
        JSONObject nick= null;

        try {

            nick = new JSONObject(json);
            Iterator iteratorN = nick.keys();

            while (iteratorN.hasNext()){

                getKey = (String) iteratorN.next();
                JSONObject group = nick.getJSONObject(getKey);
                Iterator iteratorG = group.keys();
                Gruppi gr = new Gruppi();

                while (iteratorG.hasNext()){

                    String key = (String) iteratorG.next();
                    String grou = group.getString(key);
                    gr.setNome(grou);
                }
                gruppi.add(gr);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gruppi;
    }

    public static List<Post> findPost(String json){

        List<Post> posts = new ArrayList<>();
        String key = null;
        JSONObject group = null;

        try {
            group = new JSONObject(json);
            Iterator itPost = group.keys();

            while (itPost.hasNext()){

                key = (String) itPost.next();
                JSONObject postNa = group.getJSONObject(key);
                Iterator itNumPost = postNa.keys();
                Post post = new Post();

                while (itNumPost.hasNext()){
                    String getKey = (String) itNumPost.next();
                    String s = postNa.getString(getKey);
                    if (getKey.equals("Titolo")){
                        post.setTitolo(s);
                    }
                    if (getKey.equals("Autore")){
                        post.setAutore(s);
                    }
                    if (getKey.equals("Data_creazione")){
                        post.setData(formDate(s));
                    }
                    if (getKey.equals("Contenuto")){
                        post.setContenuto(s);
                    }
                }
                posts.add(post);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static Date formDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.ITALY);
        Date datefin = new Date();
        try {
            datefin = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datefin;
    }

}
