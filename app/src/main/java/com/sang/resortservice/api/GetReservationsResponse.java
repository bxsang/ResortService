package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetReservationsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Reservation> reservations = null;

    public GetReservationsResponse(String status, List<Reservation> reservations) {
        this.status = status;
        this.reservations = reservations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
