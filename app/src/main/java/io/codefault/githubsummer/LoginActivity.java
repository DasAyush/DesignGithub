package io.codefault.githubsummer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.codefault.githubsummer.ApiInterfaces.AuthenticationService;
import io.codefault.githubsummer.ServiceClient.ServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_github_username) EditText username;

    @BindView(R.id.et_github_password) EditText password;

    @BindView(R.id.btnLogin) Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        ServiceClient.initializeClient();

        if (ServiceClient.getClient() != null) {
            Log.d("Client", "Not null");
        } else {
            Log.e("Client", "NULL");
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                AuthenticationService obj = ServiceClient.createInterface(AuthenticationService.class);

                final String encodedString = pass + ":" + userName;
                String AuthHeader = "Basic " + Base64.encodeToString(encodedString.getBytes(), Base64.NO_WRAP);

                Call<Boolean> value = obj.basicAuthUser(AuthHeader);

                value.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(getBaseContext(), "Authentication Successfull", Toast.LENGTH_SHORT).show();
                        ServiceClient.setAuthEncoded(Base64.encodeToString(encodedString.getBytes(), Base64.NO_WRAP));
                        ServiceClient.setUsername(userName);
                        Intent intent = new Intent(getBaseContext(), Index.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Authentication Not Successfull", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
