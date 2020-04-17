package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

public class BookingResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private String result;

    public BookingResponse(String status, String result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
