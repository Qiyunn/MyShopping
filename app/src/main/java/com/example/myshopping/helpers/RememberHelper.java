package com.example.myshopping.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class RememberHelper {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final String FILE_NAME="rememberInfo";
    private final String KEY="key";

    public RememberHelper(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void addRemember(String mobile){
        editor.putString(KEY,mobile);
        editor.commit();
    }

    public String retnrnRemember(){
        String s=sharedPreferences.getString(KEY,null);
        return s;
    }

    public void updateRemember(){
        editor.putString(KEY,null);
        editor.commit();
    }
}
