package com.sang.resortservice.api;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class GetUserResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("token")
    private String token;

    public GetUserResponse(int id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(this.id) + "; " + this.name + "; " + this.token;
    }
}
