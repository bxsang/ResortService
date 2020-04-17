package com.sang.resortservice.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class RoomType {
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("price")
    private int price;

    public RoomType(int id, String type, int price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return id+"; "+type;
    }
}
