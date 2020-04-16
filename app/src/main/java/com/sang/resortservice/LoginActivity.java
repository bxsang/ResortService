package com.sang.resortservice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sang.resortservice.api.APIClient;
import com.sang.resortservice.api.GetUserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    String username;
    String password;

    TextInputEditText editTextUsername;
    TextInputLayout layoutPassword;
    TextInputEditText editTextPassword;
    ExtendedFloatingActionButton buttonLogin;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        layoutPassword = findViewById(R.id.textField2);
        buttonLogin = findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(v -> {
            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();

            login();
        });
    }

    private void login() {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient client = builder.create(APIClient.class);

        Call<GetUserResult> call = client.login(username, password);

        call.enqueue(new Callback<GetUserResult>() {
            @Override
            public void onResponse(Call<GetUserResult> call, Response<GetUserResult> response) {
                if (response.isSuccessful() && response.body().getToken() != null) {
                    Log.d("Response", response.body().toString());

                    SharedPreferences sharedPreferences = getSharedPreferences("result", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    final Intent data = new Intent();
                    setResult(Activity.RESULT_OK, data);

                    finish();
                } else {
                    layoutPassword.setError(getString(R.string.login_wrong_credential));
                }
            }

            @Override
            public void onFailure(Call<GetUserResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }
}
