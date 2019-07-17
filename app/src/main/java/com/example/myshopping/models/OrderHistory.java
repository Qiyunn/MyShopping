package com.example.myshopping.models;

import java.io.Serializable;

public class OrderHistory implements Serializable {
    private String orderid;
    private String itemname;
    private String itemquantity;
    private String paidprice;
    private String placedon;

    public OrderHistory(){}

    public OrderHistory(String orderid, String itemname, String itemquantity, String paidprice, String placedon) {
        this.orderid = orderid;
        this.itemname = itemname;
        this.itemquantity = itemquantity;
        this.paidprice = paidprice;
        this.placedon = placedon;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(String itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getPaidprice() {
        return paidprice;
    }

    public void setPaidprice(String paidprice) {
        this.paidprice = paidprice;
    }

    public String getPlacedon() {
        return placedon;
    }

    public void setPlacedon(String placedon) {
        this.placedon = placedon;
    }
}
