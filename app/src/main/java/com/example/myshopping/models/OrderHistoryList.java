package com.example.myshopping.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderHistoryList implements Serializable {
    @SerializedName("Order history")
    private ArrayList<OrderHistory> arrayList;

    public OrderHistoryList() {
    }

    public ArrayList<OrderHistory> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<OrderHistory> arrayList) {
        this.arrayList = arrayList;
    }
}
