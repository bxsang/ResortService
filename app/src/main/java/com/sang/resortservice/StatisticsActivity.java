package com.sang.resortservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sang.resortservice.api.APIClient;
import com.sang.resortservice.api.GetStatisticsResponse;
import com.sang.resortservice.api.Statistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsActivity extends AppCompatActivity {
    TextView tvRoomCount;
    TextView tvCustomerCount;
    TextView tvReserationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        tvRoomCount = findViewById(R.id.tv_room_count);
        tvCustomerCount = findViewById(R.id.tv_customer_count);
        tvReserationCount = findViewById(R.id.tv_reservation_count);

        fetchStatistics();
    }

    private void fetchStatistics() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient client = retrofit.create(APIClient.class);
        Call<GetStatisticsResponse> call = client.getStatistics();

        call.enqueue(new Callback<GetStatisticsResponse>() {
            @Override
            public void onResponse(Call<GetStatisticsResponse> call, Response<GetStatisticsResponse> response) {
                setValues(response.body().getStatistics());
            }

            @Override
            public void onFailure(Call<GetStatisticsResponse> call, Throwable t) {
                Toast.makeText(StatisticsActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setValues(Statistics statistics) {
        tvRoomCount.setText(String.valueOf(statistics.getRoomCount()));
        tvCustomerCount.setText(String.valueOf(statistics.getCustomerCount()));
        tvReserationCount.setText(String.valueOf(statistics.getReservationCount()));
    }
}
