package diegoperego_it.socialnetwork;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import diegoperego_it.socialnetwork.Adapter.CardAdapter;
import diegoperego_it.socialnetwork.Model.Comunity;
import diegoperego_it.socialnetwork.Model.Gruppi;
import diegoperego_it.socialnetwork.Util.FirebaseRest;
import diegoperego_it.socialnetwork.Util.InternalStorage;
import diegoperego_it.socialnetwork.Util.JsonParser;
import diegoperego_it.socialnetwork.Util.TaskDelegate;

public class GruppiActivity extends AppCompatActivity implements TaskDelegate{

    private TextView nick;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CardAdapter cardAdapter;
    private ProgressDialog dialog;
    private List<Gruppi> gruppi;
    private Comunity comunity;
    private String nickname;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gruppi);

        nick = findViewById(R.id.tNickname);
        TaskDelegate delegate = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        nickname = preferences.getString("nickname", "");
        nick.setText(nickname);

        comunity = (Comunity)InternalStorage.readObject(getApplicationContext(), "comunity");

        nickname = nick.getText().toString();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (comunity != null){
            cardAdapter = new CardAdapter(getApplicationContext(), comunity.getGruppi());
            recyclerView.setAdapter(cardAdapter);
        }else {
            comunity = new Comunity();
            restGruppi(delegate);
        }

    }

    public void restGruppi(final TaskDelegate delegate){
        dialog = new ProgressDialog(GruppiActivity.this);
        dialog.setMessage("Caricamento gruppi");
        dialog.show();

        gruppi = new ArrayList<>();

        FirebaseRest.get("Users/" + nickname + "/Gruppi", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp = new String(responseBody);

                gruppi = JsonParser.findGroup(resp);
                cardAdapter = new CardAdapter(getApplicationContext(), gruppi);
                recyclerView.setAdapter(cardAdapter);
                comunity.setGruppi(gruppi);
                InternalStorage.writeObject(getApplicationContext(), "comunity", comunity);
                delegate.TaskCompleto("Caricamento effettuato");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                int i = statusCode;
                Log.d("Tag", ""+i);
                delegate.TaskCompleto("Caricamento fallito");
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
