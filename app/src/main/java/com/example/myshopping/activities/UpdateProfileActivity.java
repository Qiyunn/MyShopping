package com.example.myshopping.activities;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.network.VolleySingleton;

public class UpdateProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText editTextFname,editTextLname,editTextAddress,editTextEmail,editTextMobile;
    Button buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

       toolbar=findViewById(R.id.tool_bar);
       toolbar.setTitle("Update Profile");
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       init();
    }

    private void init() {
        editTextFname=findViewById(R.id.updte_profile_edit_fname);
        editTextLname=findViewById(R.id.updte_profile_edit_lname);
        editTextAddress=findViewById(R.id.updte_profile_edit_address);
        editTextEmail=findViewById(R.id.updte_profile_edit_email);
        editTextMobile=findViewById(R.id.updte_profile_edit_mobile);

        buttonUpdate=findViewById(R.id.updte_profile_button_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateProfile(editTextFname.getText().toString(),
                             editTextLname.getText().toString(),
                             editTextAddress.getText().toString(),
                             editTextEmail.getText().toString(),
                             editTextMobile.getText().toString()
                       );
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void updateProfile(String fname,String lname,String address,String email,String mobile){
        String url= EndPoint.updateProfile(fname,lname,address,email,mobile);
        Log.d("hi","the Url is"+url);

        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("hi","response is: "+response.toString());
                        Toast.makeText(UpdateProfileActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Log.d("hi",error.getMessage());
                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);
    }
}
