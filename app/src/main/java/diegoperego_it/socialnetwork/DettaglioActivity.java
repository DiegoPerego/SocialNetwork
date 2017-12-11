package diegoperego_it.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import diegoperego_it.socialnetwork.Model.Post;
import diegoperego_it.socialnetwork.R;
import diegoperego_it.socialnetwork.Util.InternalStorage;

public class DettaglioActivity extends AppCompatActivity {

    private TextView postName;
    private TextView postAutor;
    private TextView postContent;
    private TextView postDate;
    private String postTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio);

        postName = findViewById(R.id.tPostName);
        postAutor = findViewById(R.id.tPostAutor);
        postContent = findViewById(R.id.tContenuto);
        postDate = findViewById(R.id.tPostDate);

        postTitle = getIntent().getStringExtra("titolo");

        /*postName.setText(post.getTitolo());
        postAutor.setText(post.getAutore());
        postDate.setText(formatDate(post.getData()));
        postContent.setText(post.getContenuto());*/

    }

    public String formatDate(Date date){
        Format format = new SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.ITALY);
        return format.format(date);
    }
}
