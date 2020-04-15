package com.sang.resortservice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class GreetingActivity extends AppCompatActivity {

    private static final int AUTHENTICATION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (isLoggedIn()) {
            Intent startupIntent = new Intent(this, MainActivity.class);
            startupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startupIntent);
            finish();
        } else {
            Intent startupIntent = new Intent(this, LoginActivity.class);
            startActivityForResult(startupIntent, AUTHENTICATION_REQUEST_CODE);
        }
    }

    private boolean isLoggedIn() {
        return this.getSharedPreferences("result", MODE_PRIVATE).getBoolean("isLoggedIn", false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHENTICATION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Intent startupIntent = new Intent(this, MainActivity.class);
            startupIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(startupIntent);
        }

        finish();
    }
}
