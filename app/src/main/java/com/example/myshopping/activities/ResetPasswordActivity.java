package com.example.myshopping.activities;

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

public class ResetPasswordActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText editTextMobile,editTextPass,editTextNewpass;
    Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Reset Password");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    private void init() {
        editTextMobile=findViewById(R.id.reset_password_edit_mobile);
        editTextPass=findViewById(R.id.reset_password_edit_password);
        editTextNewpass=findViewById(R.id.reset_password_edit_newpass);
        buttonReset=findViewById(R.id.reset_password_button_reset);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword(editTextMobile.getText().toString(),editTextPass.getText().toString(),
                        editTextNewpass.getText().toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();

        return super.onOptionsItemSelected(item);
    }

    private void resetpassword(String mobile,String password,String newPasseord){
        String url= EndPoint.resetPassword(mobile,password,newPasseord);
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ResetPasswordActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

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
