package diegoperego_it.socialnetwork.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;

import diegoperego_it.socialnetwork.Model.Gruppi;

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
                getKey = (String)iteratorN.next();
                JSONObject gruop = nick.getJSONObject(getKey);
                Iterator iteratorG = gruop.keys();
                while (iteratorG.hasNext()){
                    String key = (String) iteratorG.next();
                    JSONObject nome = nick.getJSONObject(key);
                    Gruppi gr = new Gruppi();
                    Iterator iteratorNa = nome.keys();
                    while (iteratorNa.hasNext()){
                        String na = (String) iteratorNa.next();
                        String s = nome.getString(na);
                        gr.setNome(s);
                    }
                    gruppi.add(gr);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gruppi;
    }

}
