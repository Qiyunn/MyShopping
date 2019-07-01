package com.example.myshopping.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextMobile, editTextPassword;
    Button buttonSIgnIn, buttonNeedAccount;
    SharedPreferences sharedPreferences;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(savedInstanceState!=null){
//            String mobile=savedInstanceState.getString("mobile");
//            editTextMobile.setText(mobile);
//        }
        setContentView(R.layout.activity_login);

        init();
    }


    private void init() {
        editTextMobile = findViewById(R.id.login_activity_edit_mobile);
        editTextPassword = findViewById(R.id.login_activity_edit_password);

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
//                Log.d("data123","the apiKey is:"+apiKey);

                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                break;
            case R.id.login_activity_button_need_account:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }

    }

    private void login(String mobile, String password) {
        final String url = EndPoint.loginUser(mobile, password);
        Log.d("data123", "url is:"+url);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("data123", "response is:"+response.toString());
                        Toast.makeText(LoginActivity.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = (JSONObject) response.get(0);
                            Log.d("data123","the jsonObject is:"+jsonObject);
                            String apiKey = jsonObject.getString("appapikey ");
                            String userId= jsonObject.getString("id");

                            sharedPreferences=getSharedPreferences("LOGIN_DATA",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("API_KEY",apiKey);
                            editor.putString("USER_ID",userId);
                            editor.commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("data123", error.getMessage());
                    }
                }

        );
        VolleySingleton.getInstance().addToRequestQue(request);
    }

}
