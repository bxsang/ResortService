package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

public class Statistics {
    @SerializedName("room_count")
    private int roomCount;
    @SerializedName("customer_count")
    private int customerCount;
    @SerializedName("reservation_count")
    private int reservationCount;

    public Statistics(int roomCount, int customerCount, int reservationCount) {
        this.roomCount = roomCount;
        this.customerCount = customerCount;
        this.reservationCount = reservationCount;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }
}
