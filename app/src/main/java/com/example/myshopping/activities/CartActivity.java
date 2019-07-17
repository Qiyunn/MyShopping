package com.example.myshopping.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myshopping.R;
import com.example.myshopping.databases.DBHelper;
import com.example.myshopping.fragments.EmptyCartFragment;
import com.example.myshopping.fragments.NormalCartFragment;
import com.example.myshopping.models.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;

    ArrayList<Cart> arrayList = new ArrayList<>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {
        frameLayout = findViewById(R.id.cart_activity_frame_layout);
        fragmentManager = getSupportFragmentManager();

        dbHelper = new DBHelper(this);
        arrayList=dbHelper.selectFromCart();
        if(arrayList.size()==0){
            fragmentManager.beginTransaction().add(R.id.cart_activity_frame_layout,new EmptyCartFragment()).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.cart_activity_frame_layout,new NormalCartFragment()).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
