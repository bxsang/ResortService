package com.sang.resortservice;

public class BookingValues {
    public String customerName;
    public String customerPhone;
    public String customerGender;
    public String customerEmail;
    public String customerAddress;
    public int roomId;
    public Long date1;
    public Long date2;

    public BookingValues(String customerName, String customerPhone, String customerGender,
                         String customerEmail, String customerAddress, int roomId, Long date1, Long date2) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerGender = customerGender;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.roomId = roomId;
        this.date1 = date1;
        this.date2 = date2;
    }
}
