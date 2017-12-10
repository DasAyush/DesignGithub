package io.codefault.githubsummer;

import android.app.ProgressDialog;
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

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.Theme_AppCompat_Light_Dialog_MinWidth);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                AuthenticationService obj = ServiceClient.createInterface(AuthenticationService.class);

                final String encodedString = userName + ":" + pass;
                String AuthHeader = "Basic " + Base64.encodeToString(encodedString.getBytes(), Base64.NO_WRAP);

                Call<Void> value = obj.basicAuthUser(AuthHeader);

                value.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getBaseContext(), "Authentication Successfull", Toast.LENGTH_SHORT).show();
                        ServiceClient.setAuthEncoded(Base64.encodeToString(encodedString.getBytes(), Base64.NO_WRAP));
                        ServiceClient.setUsername(userName);
                        Intent intent = new Intent(getBaseContext(), Index.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(getBaseContext(), "Authentication Not Successfull", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
