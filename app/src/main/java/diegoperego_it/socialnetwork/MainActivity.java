package diegoperego_it.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import diegoperego_it.socialnetwork.Util.InternalStorage;

public class MainActivity extends AppCompatActivity {

    private String nickname;
    private Intent login;
    private Intent gruppi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nickname = (String) InternalStorage.readObject(getApplicationContext(), "nick");
        login = new Intent(getApplicationContext(), LoginActivity.class);
        gruppi = new Intent(getApplicationContext(), GruppiActivity.class);

        if(nickname != null){
            startActivity(gruppi);
        }else {
            startActivity(login);
        }
    }
}
