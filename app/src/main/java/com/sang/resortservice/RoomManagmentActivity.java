package com.sang.resortservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sang.resortservice.api.APIClient;
import com.sang.resortservice.api.GetRoomsResponse;
import com.sang.resortservice.api.Room;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomManagmentActivity extends AppCompatActivity {

    private List<Room> listRoom = new ArrayList<>();
    private RecyclerView recyclerView;
    private RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_managment);

        fetchRooms();
        recyclerView = findViewById(R.id.rv_room_mgmt);
        adapter = new RoomAdapter(this, listRoom);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void fetchRooms() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient client = retrofit.create(APIClient.class);
        Call<GetRoomsResponse> call = client.getAllRooms();

        call.enqueue(new Callback<GetRoomsResponse>() {
            @Override
            public void onResponse(Call<GetRoomsResponse> call, Response<GetRoomsResponse> response) {
                prepareData(response.body().getRoom());
            }

            @Override
            public void onFailure(Call<GetRoomsResponse> call, Throwable t) {
                Toast.makeText(RoomManagmentActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareData(List<Room> rooms) {
        for (int i=0; i<rooms.size(); i++) {
            listRoom.add(rooms.get(i));
        }
    }
}
