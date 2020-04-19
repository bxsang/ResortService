package com.sang.resortservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static int REQUEST_CODE = 100;

    DrawerLayout drawerLayout;
    MaterialToolbar topAppBar;
    GridLayout gridLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.layout_drawer);
        topAppBar = findViewById(R.id.topAppBar);
        gridLayout = findViewById(R.id.gl_main);
        navigationView = findViewById(R.id.navigation_drawer);

        setClickEvent(gridLayout);

        topAppBar.setNavigationOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START));

        topAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.search:
                    Toast.makeText(MainActivity.this, "Search button clicked", Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            drawerLayout.closeDrawers();
            switch (item.getItemId()) {
                case R.id.nv_menu_booking:
                    startActivityForResult(new Intent(MainActivity.this, BookingActivity.class), REQUEST_CODE);
                case R.id.nv_menu_room_mgmt:
                    startActivity(new Intent(MainActivity.this, RoomManagmentActivity.class));
                case R.id.nv_menu_reservation_mgnt:
                    startActivity(new Intent(MainActivity.this, ReservationManagmentActivity.class));
                case R.id.nv_menu_rating:
                    startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                case R.id.nv_menu_statistics:
                    startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
                case R.id.nv_menu_logout:
                    logout();
                default:

            }
            return true;
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
                        startActivity(new Intent(MainActivity.this, RoomManagmentActivity.class)));
            } else if (id == R.id.cv_customer) {
                cardView.setOnClickListener(v ->
                        startActivity(new Intent(MainActivity.this, ReservationManagmentActivity.class)));
            } else if (id == R.id.cv_rating) {
                cardView.setOnClickListener(v ->
                        startActivity(new Intent(MainActivity.this, FeedbackActivity.class)));
            } else if (id == R.id.cv_statistics) {
                cardView.setOnClickListener(v ->
                        startActivity(new Intent(MainActivity.this, StatisticsActivity.class)));
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
