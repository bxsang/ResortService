package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

public class GetPriceResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private long price;

    public GetPriceResponse(String status, long price) {
        this.status = status;
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
