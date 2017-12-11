package diegoperego_it.socialnetwork;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import diegoperego_it.socialnetwork.Adapter.PostAdapter;
import diegoperego_it.socialnetwork.Model.Comunity;
import diegoperego_it.socialnetwork.Model.Gruppi;
import diegoperego_it.socialnetwork.Model.Post;
import diegoperego_it.socialnetwork.Util.FirebaseRest;
import diegoperego_it.socialnetwork.Util.InternalStorage;
import diegoperego_it.socialnetwork.Util.JsonParser;
import diegoperego_it.socialnetwork.Util.TaskDelegate;

public class PostActivity extends AppCompatActivity implements TaskDelegate{

    private TextView nomegruppo;
    private String name;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;
    private List<Post> posts;
    private PostAdapter adapter;
    private Comunity comunity;
    private Gruppi group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TaskDelegate delegate = this;

        nomegruppo = findViewById(R.id.tgruppoN);
        nomegruppo.setText(name);

        recyclerView = findViewById(R.id.recyclerPost);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        name = (String) InternalStorage.readObject(getApplicationContext(), "gruppo");
        comunity = (Comunity)InternalStorage.readObject(getApplicationContext(), "comunity");
        group = comunity.checkGruppo(name);

        if(group.getPosts().size() != 0){
            adapter = new PostAdapter(this, group.getPosts());
            recyclerView.setAdapter(adapter);
        }else {
            restPost(delegate);
        }
    }

    public void restPost(final TaskDelegate delegate){
        dialog = new ProgressDialog(PostActivity.this);
        dialog.setMessage("Caricamento Post");
        dialog.show();

        posts = new ArrayList<>();

        FirebaseRest.get("Comunities/Gruppi/" + name + "/Post", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp = new String (responseBody);
                posts = JsonParser.findPost(resp);
                adapter = new PostAdapter(getApplicationContext(), posts);
                recyclerView.setAdapter(adapter);
                comunity.updatePost(name, posts);
                InternalStorage.writeObject(getApplicationContext(), "comunity", comunity);
                delegate.TaskCompleto("Caricamento Effettuato");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.TaskCompleto("Caricamento Fallito");
            }
        });

    }

    @Override
    public void TaskCompleto(String s) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}


