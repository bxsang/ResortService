package com.sang.resortservice.api;

import com.google.gson.annotations.SerializedName;

public class Feedback {
    @SerializedName("id")
    private int id;
    @SerializedName("star")
    private int star;
    @SerializedName("comment")
    private String comment;
    @SerializedName("customer_id")
    private int customerId;

    public Feedback(int id, int star, String comment, int customerId) {
        this.id = id;
        this.star = star;
        this.comment = comment;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
