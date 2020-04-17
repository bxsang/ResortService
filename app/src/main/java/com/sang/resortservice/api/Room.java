package com.sang.resortservice.api;

public class Room {
    private int id;
    private String name;
    private int status;
    private String type;
    private int price;

    public Room(int id, String name, int status, String type, int price) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.type = type;
        this.price = price;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
