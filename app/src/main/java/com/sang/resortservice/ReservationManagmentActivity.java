package com.sang.resortservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sang.resortservice.api.APIClient;
import com.sang.resortservice.api.GetReservationsResponse;
import com.sang.resortservice.api.Reservation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationManagmentActivity extends AppCompatActivity {

    private List<Reservation> listReservation = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_managment);

        fetchReservation();
        recyclerView = findViewById(R.id.rv_rev_mgmt);
        adapter = new ReservationAdapter(this, listReservation);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void fetchReservation() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient client = retrofit.create(APIClient.class);
        Call<GetReservationsResponse> call = client.getAllReservations();

        call.enqueue(new Callback<GetReservationsResponse>() {
            @Override
            public void onResponse(Call<GetReservationsResponse> call, Response<GetReservationsResponse> response) {
                prepareData(response.body().getReservations());
            }

            @Override
            public void onFailure(Call<GetReservationsResponse> call, Throwable t) {
                Toast.makeText(ReservationManagmentActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareData(List<Reservation> reservations) {
        for (int i=0; i<reservations.size(); i++) {
            listReservation.add(reservations.get(i));
        }
    }
}
