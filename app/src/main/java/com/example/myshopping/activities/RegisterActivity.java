package com.example.myshopping.activities;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFname,editTextLname,editTextAddress,editTextEmail,editTextMobile,editTextPassword;
    Button buttonSignUp,buttonAlreadyHave;
    UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userHelper=new UserHelper();
        User user=userHelper.returnUser();
        if(user.getAppapikey()!=null){
            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
        }else{
        init();
        }
    }

    private void init() {
        editTextFname=findViewById(R.id.register_activity_edit_fname);
        editTextLname=findViewById(R.id.register_activity_edit_lname);
        editTextAddress=findViewById(R.id.register_activity_edit_address);
        editTextEmail=findViewById(R.id.register_activity_edit_email);
        editTextMobile=findViewById(R.id.register_activity_edit_mobile);
        editTextPassword=findViewById(R.id.register_activity_edit_password);

        buttonSignUp=findViewById(R.id.register_activity_button_sign_up);
        buttonAlreadyHave=findViewById(R.id.register_activity_already_registed);

        buttonSignUp.setOnClickListener(this);
        buttonAlreadyHave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register_activity_button_sign_up:
                register(editTextFname.getText().toString(),
                        editTextLname.getText().toString(),
                        editTextAddress.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextMobile.getText().toString(),
                        editTextPassword.getText().toString());
                break;
            case R.id.register_activity_already_registed:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
        }

    }

    private void register(String fname,String lname,String address,String email,String mobile,String password){
        String url= EndPoint.registerUser(fname,lname,address,email,mobile,password);
        Log.d("data", url);
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         Log.d("data",response.toString());
                        Toast.makeText(RegisterActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("data",error.getMessage());
                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);
    }

}
