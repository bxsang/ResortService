package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetStatisticsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private Statistics statistics;

    public GetStatisticsResponse(String status, Statistics statistics) {
        this.status = status;
        this.statistics = statistics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
