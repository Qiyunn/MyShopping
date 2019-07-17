package com.example.myshopping.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CouponList {
    @SerializedName("Coupon discount")
   private ArrayList<Coupon> arrayList;

    public ArrayList<Coupon> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Coupon> arrayList) {
        this.arrayList = arrayList;
    }
}
