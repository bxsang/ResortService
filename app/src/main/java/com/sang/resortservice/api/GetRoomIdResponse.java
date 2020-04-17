package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

public class GetRoomIdResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private int id;

    public GetRoomIdResponse(String status, int id) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
