package com.sang.resortservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sang.resortservice.api.APIClient;
import com.sang.resortservice.api.Feedback;
import com.sang.resortservice.api.GetFeedbackResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackActivity extends AppCompatActivity {

    private List<Feedback> feedbackList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FeedbackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        fetchFeedbacks();
        recyclerView = findViewById(R.id.rv_feedback);
        adapter = new FeedbackAdapter(this, feedbackList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void fetchFeedbacks() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient client = retrofit.create(APIClient.class);
        Call<GetFeedbackResponse> call = client.getAllFeedbacks();

        call.enqueue(new Callback<GetFeedbackResponse>() {
            @Override
            public void onResponse(Call<GetFeedbackResponse> call, Response<GetFeedbackResponse> response) {
                prepareData(response.body().getFeedbacks());
            }

            @Override
            public void onFailure(Call<GetFeedbackResponse> call, Throwable t) {
                Toast.makeText(FeedbackActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareData(List<Feedback> feedbacks) {
        for (int i=0; i<feedbacks.size(); i++) {
            feedbackList.add(feedbacks.get(i));
        }
    }
}
