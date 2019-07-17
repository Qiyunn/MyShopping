package com.example.myshopping.models;

import java.io.Serializable;

public class SubCategory implements Serializable {

    private String scid;
    private String scname;
    private String scdiscription;
    private String scimageurl;

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getScdiscription() {
        return scdiscription;
    }

    public void setScdiscription(String scdiscription) {
        this.scdiscription = scdiscription;
    }

    public String getScimageurl() {
        return scimageurl;
    }

    public void setScimageurl(String scimageurl) {
        this.scimageurl = scimageurl;
    }
}
