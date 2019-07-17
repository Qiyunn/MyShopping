package com.example.myshopping.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.myshopping.app.MyApplication;
import com.example.myshopping.models.User;

public class UserHelper {
    private String FILE_NAME="userInfo";

    private String API_KEY="apiKey";
    private String USER_ID="userId";
    private String FNAME="fname";
    private String LNAME="lname";
    private String EMAIL="email";
    private String PHONE="phone";


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public UserHelper() {
        context = MyApplication.getAppContext();
        sharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void addUser(User user) {
          editor.putString(API_KEY,user.getAppapikey());
          editor.putString(USER_ID,user.getId());
          editor.putString(FNAME,user.getFirstname());
          editor.putString(LNAME,user.getLastname());
          editor.putString(EMAIL,user.getEmail());
          editor.putString(PHONE,user.getMobile());


          editor.commit();
    }

    public User returnUser(){
        User user=new User();
        user.setId(sharedPreferences.getString(USER_ID,null));
        user.setAppapikey(sharedPreferences.getString(API_KEY,null));
        user.setFirstname(sharedPreferences.getString(FNAME,null));
        user.setLastname(sharedPreferences.getString(LNAME,null));
        user.setEmail(sharedPreferences.getString(EMAIL,null));
        user.setMobile(sharedPreferences.getString(PHONE,null));
        return user;
    }

    public void clearEditor(){
        editor.clear().commit();
        Toast.makeText(context, "You are Logged out now!", Toast.LENGTH_SHORT).show();
    }
}
