package com.example.myshopping.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SubCategoryList implements Serializable{
    @SerializedName("subcategory")
    private ArrayList<SubCategory> arrayList;

    public SubCategoryList() {
    }

    public ArrayList<SubCategory> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<SubCategory> arrayList) {
        this.arrayList = arrayList;
    }
}
