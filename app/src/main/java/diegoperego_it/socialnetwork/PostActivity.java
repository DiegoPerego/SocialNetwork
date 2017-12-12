package diegoperego_it.socialnetwork;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import diegoperego_it.socialnetwork.Util.CardViewCL;
import diegoperego_it.socialnetwork.Util.FirebaseRest;
import diegoperego_it.socialnetwork.Util.InternalStorage;
import diegoperego_it.socialnetwork.Util.JsonParser;
import diegoperego_it.socialnetwork.Util.TaskDelegate;

public class PostActivity extends AppCompatActivity implements TaskDelegate, CardViewCL{

    private TextView nomegruppo;
    private String name;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProgressDialog dialog;
    private List<Post> posts;
    private PostAdapter adapter;
    private Comunity comunity;
    private Gruppi group;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TaskDelegate delegate = this;

        nomegruppo = findViewById(R.id.tgruppoN);
        fab = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recyclerPost);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        name = (String) InternalStorage.readObject(getApplicationContext(), "gruppo");
        comunity = (Comunity)InternalStorage.readObject(getApplicationContext(), "comunity");
        nomegruppo.setText(name);
        group = comunity.checkGruppo(name);

        if(group.getPosts().size() != 0){
            adapter = new PostAdapter(this, group.getPosts(), this);
            recyclerView.setAdapter(adapter);
        }else {
            restPost(delegate);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(i);
            }
        });
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

    @Override
    public void onCardClick(int pos, Post post, CardView cardView) {
        Intent intent = new Intent(this, DettaglioActivity.class);
        intent.putExtra("titolo", post.getTitolo());

        ActivityOptions activityOptions = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, cardView, "detail");
        }
        startActivity(intent, activityOptions.toBundle());
    }
}


