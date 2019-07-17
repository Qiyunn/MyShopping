package com.example.myshopping.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myshopping.R;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.User;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonResetPassword,buttonUpdateProfile;
    TextView textViewFname,textViewLname,textViewEmail,textViewMobile;
    User user;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("My Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {
        UserHelper userHelper=new UserHelper();
        user= userHelper.returnUser();

        textViewFname=findViewById(R.id.profile_text_fname);
        textViewLname=findViewById(R.id.profile_text_lname);
        textViewEmail=findViewById(R.id.profile_text_email);
        textViewMobile=findViewById(R.id.profile_text_mobile);

        textViewFname.setText("FIRST NAME: "+user.getFirstname());
        textViewLname.setText("LAST NAME:"+user.getLastname());
        textViewEmail.setText("EMAIL: "+user.getEmail());
        textViewMobile.setText("MOBILE: "+user.getMobile());

        buttonUpdateProfile=findViewById(R.id.profile_button_update_profile);
        buttonResetPassword=findViewById(R.id.profile_button_reset_password);


        buttonUpdateProfile.setOnClickListener(this);
        buttonResetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.profile_button_update_profile:
                startActivity(new Intent(ProfileActivity.this,UpdateProfileActivity.class));
                break;
            case R.id.profile_button_reset_password:
                startActivity(new Intent(ProfileActivity.this,ResetPasswordActivity.class));
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return  true;
    }
}
