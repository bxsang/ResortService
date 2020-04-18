package com.sang.resortservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    private static int REQUEST_CODE = 100;

    MaterialToolbar topAppBar;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAppBar = findViewById(R.id.topAppBar);
        gridLayout = findViewById(R.id.gl_main);

        setClickEvent(gridLayout);

        topAppBar.setNavigationOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Clicked!!", Toast.LENGTH_LONG).show());
        topAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.search:
                    Toast.makeText(MainActivity.this, "Search button clicked", Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        });
    }

    private void setClickEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            int id = cardView.getId();
            if (id == R.id.cv_booking) {
                cardView.setOnClickListener(v ->
                        startActivityForResult(new Intent(MainActivity.this, BookingActivity.class), REQUEST_CODE));
            } else if (id == R.id.cv_room) {
                cardView.setOnClickListener(v ->
                        startActivityForResult(new Intent(MainActivity.this, RoomManagmentActivity.class), REQUEST_CODE));
            } else if (id == R.id.cv_customer) {
                cardView.setOnClickListener(v ->
                        startActivityForResult(new Intent(MainActivity.this, ReservationManagmentActivity.class), REQUEST_CODE));
            } else if (id == R.id.cv_rating) {
                cardView.setOnClickListener(v ->
                        startActivityForResult(new Intent(MainActivity.this, FeedbackActivity.class), REQUEST_CODE));
            } else if (id == R.id.cv_statistics) {
                cardView.setOnClickListener(v ->
                        startActivityForResult(new Intent(MainActivity.this, StatisticsActivity.class), REQUEST_CODE));
            } else if (id == R.id.cv_logout) {
                cardView.setOnClickListener(v ->
                        logout());
            }
        }
    }

    private void logout() {
        Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getSharedPreferences("result", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Intent intent = new Intent(this, GreetingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }
}
