package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

public class Reservation {
    @SerializedName("id")
    private int id;
    @SerializedName("customer_id")
    private int customerId;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("room_id")
    private int roomId;
    @SerializedName("room_name")
    private String roomName;
    @SerializedName("reservation_date")
    private String reservationDate;
    @SerializedName("price")
    private int price;
    @SerializedName("payment_status")
    private int paymentStatus;
    @SerializedName("checkin_date")
    private String checkinDate;
    @SerializedName("checkout_date")
    private String checkoutDate;
    @SerializedName("is_checked_in")
    private int isCheckedIn;
    @SerializedName("is_checked_out")
    private int isCheckedOut;

    public Reservation(int id, int customerId, String customerName, int roomId, String roomName, String reservationDate, int price, int paymentStatus,
                       String checkinDate, String checkoutDate, int isCheckedIn, int isCheckedOut) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.roomId = roomId;
        this.roomName = roomName;
        this.reservationDate = reservationDate;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.isCheckedIn = isCheckedIn;
        this.isCheckedOut = isCheckedOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getIsCheckedIn() {
        return isCheckedIn;
    }

    public void setIsCheckedIn(int isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public int getIsCheckedOut() {
        return isCheckedOut;
    }

    public void setIsCheckedOut(int isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }
}
