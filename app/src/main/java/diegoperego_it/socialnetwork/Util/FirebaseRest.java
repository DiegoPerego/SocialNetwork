package diegoperego_it.socialnetwork.Util;

import com.loopj.android.http.*;

/**
 * Created by utente3.academy on 06-Dec-17.
 */

public class FirebaseRest {

    private static final String baseUrl = "https://socialnetwork-329ea.firebaseio.com/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(getTotalUrl(url), requestParams, asyncHttpResponseHandler);
    }

    public static void post(String url, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(getTotalUrl(url), requestParams, asyncHttpResponseHandler);
    }

    private static String getTotalUrl(String relativeUrl){
        return baseUrl + relativeUrl + ".json";
    }
}
