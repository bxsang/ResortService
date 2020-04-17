package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRoomTypesResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<RoomType> roomTypes = null;

    public GetRoomTypesResponse(String status, List<RoomType> roomTypes) {
        this.status = status;
        this.roomTypes = roomTypes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
