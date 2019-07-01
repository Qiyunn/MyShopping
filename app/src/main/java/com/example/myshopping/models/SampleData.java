package com.example.myshopping.models;

import android.media.Image;

import com.example.myshopping.R;

public class SampleData {

    public static final String ONE = "one";
    public static final String TWO = "two";
    public static final String THREE = "three";
    public static final String FOUR = "four";
    public static final String FIVE = "five";

    public static int getData(String title) {
        int id = 0;
        switch (title) {
            case ONE:
                id = R.drawable.one;
                return id;
            case TWO:
                id = R.drawable.two;
                return id;
            case THREE:
                id = R.drawable.three;
                return id;
            case FOUR:
                id = R.drawable.four;
                return id;
            case FIVE:
                id = R.drawable.five;
                return id;
            default:
                return 0;
        }
    }
}
