package com.example.myshopping.models;

public class Coupon {
    private String couponno;
    private String discount;

    public Coupon(String couponno, String discount) {
        this.couponno = couponno;
        this.discount = discount;
    }

    public Coupon() {
    }

    public String getCouponno() {
        return couponno;
    }

    public void setCouponno(String couponno) {
        this.couponno = couponno;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
