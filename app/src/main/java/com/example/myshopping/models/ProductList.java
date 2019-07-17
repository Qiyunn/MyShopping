package com.example.myshopping.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductList implements Serializable {

    @SerializedName("products")
    private ArrayList<Product> arrayList;

    public ArrayList<Product> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
    }
}
