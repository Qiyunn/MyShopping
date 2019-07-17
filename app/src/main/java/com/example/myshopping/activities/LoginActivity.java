package com.example.myshopping.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.helpers.RememberHelper;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.LoginResponse;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextMobile, editTextPassword;
    Button buttonSIgnIn, buttonNeedAccount;
    RememberHelper helper;


//    SharedPreferences sharedPreferences;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(savedInstanceState!=null){
//            String mobile=savedInstanceState.getString("mobile");
//            editTextMobile.setText(mobile);
//        }
        helper=new RememberHelper(this);
        setContentView(R.layout.activity_login);
        checkBox=findViewById(R.id.login_activity_check_box);

        init();
    }




    private void init() {
        editTextMobile = findViewById(R.id.login_activity_edit_mobile);
        editTextPassword = findViewById(R.id.login_activity_edit_password);

        if(helper.retnrnRemember()!=null){
            editTextMobile.setText(helper.retnrnRemember());
        }

        buttonSIgnIn = findViewById(R.id.login_activity_button_sign_in);
        buttonNeedAccount = findViewById(R.id.login_activity_button_need_account);

        buttonSIgnIn.setOnClickListener(this);
        buttonNeedAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_button_sign_in:
                login(editTextMobile.getText().toString(),
                        editTextPassword.getText().toString());
//                Log.d("login","the apiKey is:"+apiKey);

                if(checkBox.isChecked()){
                    helper.addRemember(editTextMobile.getText().toString());
                }else{
                    helper.updateRemember();
                }

                break;
            case R.id.login_activity_button_need_account:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void login(String mobile, String password) {
        final String url = EndPoint.loginUser(mobile, password);
        Log.d("login", "url is:" + url);
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Mytag", "response: " + response);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        if (response.charAt(0) == '{') {
                            LoginResponse loginResponse=gson.fromJson(response,LoginResponse.class);
                            ArrayList<String> arrayList=loginResponse.getArrayList();
                            if(arrayList.get(0).equals("Mobile number not register")){
                                Log.i("Mytag", "arraylist not null");
                            Toast.makeText(LoginActivity.this, ""+arrayList.get(0), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, "The password is not valid!", Toast.LENGTH_SHORT).show();

                            }


                        } else if(response.charAt(0) == '['){

                            Type listType = new TypeToken<List<User>>() {
                            }.getType();
                            List<User> list = gson.fromJson(response.toString(), listType);
                            User user = list.get(0);
                            UserHelper userHelper = new UserHelper();
                            userHelper.addUser(user);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "try in next 5 minuts!", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("data123", error.getMessage());
                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);
    }


}
