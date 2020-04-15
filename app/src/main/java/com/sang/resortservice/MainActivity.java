package com.sang.resortservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    static final String[] gridViewGridValue = new String[] {
            "Grid 1", "Grid 2", "Grid 3", "Grid 4", "Grid 5", "Grid 6", "Grid 7", "Grid 8",
            "Grid 9", "Grid 10", "Grid 11", "Grid 12", "Grid 13", "Grid 14", "Grid 15", "Grid 16",
            "Grid 17", "Grid 18", "Grid 19", "Grid 20", "Grid 21", "Grid 22", "Grid 23", "Grid 24",
            "Grid 25", "Grid 26", "Grid 27", "Grid 28", "Grid 29", "Grid 30", "Grid 31", "Grid 32",
            "Grid 33"
    };

    MaterialToolbar topAppBar;
    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAppBar = findViewById(R.id.topAppBar);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked!!", Toast.LENGTH_LONG).show();
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Toast.makeText(MainActivity.this, "Search button clicked", Toast.LENGTH_LONG).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        mGridView = findViewById(R.id.grid_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, gridViewGridValue);

        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
