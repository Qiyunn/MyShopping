package com.example.myshopping.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryList {
    @SerializedName("category")
    private ArrayList<Category> arrayList;

    public ArrayList<Category> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Category> arrayList) {
        this.arrayList = arrayList;
    }
}
