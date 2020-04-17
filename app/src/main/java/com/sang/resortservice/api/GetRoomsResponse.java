package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRoomsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Room> room = null;

    public GetRoomsResponse(String status, List<Room> room) {
        this.status = status;
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }
}
