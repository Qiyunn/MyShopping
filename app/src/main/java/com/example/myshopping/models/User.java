package com.example.myshopping.models;

import com.google.gson.annotations.SerializedName;

public class User {

    private String msg;
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobile;
    @SerializedName("appapikey ")
    private String appapikey;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
