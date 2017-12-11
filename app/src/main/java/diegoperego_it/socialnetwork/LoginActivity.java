package diegoperego_it.socialnetwork;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import diegoperego_it.socialnetwork.Util.FirebaseRest;
import diegoperego_it.socialnetwork.Util.InternalStorage;
import diegoperego_it.socialnetwork.Util.TaskDelegate;

public class LoginActivity extends AppCompatActivity implements TaskDelegate {

    private EditText nickname;
    private EditText password;
    private Button login;
    private ProgressDialog dialog;
    private Intent gruppi;
    private String nick;
    private String nickPref;
    private String pass;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nickname = findViewById(R.id.eNickname);
        password = findViewById(R.id.ePassword);
        login = findViewById(R.id.bLogin);
        final TaskDelegate delegate = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.getString("nickname", "");

        gruppi = new Intent(getApplicationContext(), GruppiActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = nickname.getText().toString();
                pass = password.getText().toString();
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Caricamento");
                dialog.show();

                final SharedPreferences.Editor editor = preferences.edit();
                nickPref = preferences.getString("nickname", "");

                FirebaseRest.get("Users/" + nick + "/Password/", null, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if(statusCode == 200) {
                            String resp = new String(responseBody);
                            String s = resp.substring(1, resp.length()-1);
                            if (!pass.equals("null")) {
                                if (pass.equals(s)) {
                                    editor.putString("nickname", nick);
                                    editor.apply();
                                    startActivity(gruppi);
                                    delegate.TaskCompleto("Caricamento completato");
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password errata", Toast.LENGTH_SHORT).show();
                                    delegate.TaskCompleto("Errore");
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Password non inserita", Toast.LENGTH_SHORT).show();
                                delegate.TaskCompleto("Errore");
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        delegate.TaskCompleto("User errato ");
                    }
                });
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
