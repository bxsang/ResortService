package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFeedbackResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Feedback> feedbacks = null;

    public GetFeedbackResponse(String status, List<Feedback> feedbacks) {
        this.status = status;
        this.feedbacks = feedbacks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
