package diegoperego_it.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import diegoperego_it.socialnetwork.Util.InternalStorage;

public class GruppiActivity extends AppCompatActivity {

    private TextView nick;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gruppi);

        nick = findViewById(R.id.tNickname);

        nick.setText((String) InternalStorage.readObject(getApplicationContext(), "nick"));

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        

    }
}
