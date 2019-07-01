package com.example.myshopping.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.adapters.HomeActivityRecyclerViewAdapter;
import com.example.myshopping.adapters.ProductFragmentAdapter;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.models.Category;
import com.example.myshopping.models.CategoryList;
import com.example.myshopping.models.SampleData;
import com.example.myshopping.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;

    ViewPager viewPager;
    ProductFragmentAdapter adapter;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getCategory();
    }

    private void init() {
        setUpToolbar();
        setUpNav();

        setUpViewPager();
    }



    private void setUpNav() {
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.home_nav_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav_drawer,R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void setUpViewPager() {

        viewPager=findViewById(R.id.home_activity_view_pager);
        adapter=new ProductFragmentAdapter(getSupportFragmentManager());

        adapter.addFragment(SampleData.ONE);
        adapter.addFragment(SampleData.TWO);
        adapter.addFragment(SampleData.THREE);
        adapter.addFragment(SampleData.FOUR);
        adapter.addFragment(SampleData.FIVE);

        viewPager.setAdapter(adapter);

    }


    private void setUpToolbar() {
        toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        textView=findViewById(R.id.tool_bar_text_title);
        textView.setText("HOME");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_item_search:
                getCategory();
                break;
            case R.id.main_menu_item_cart:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nev_profile:
                startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
                break;
            case R.id.nev_orders:
                break;
            case R.id.nev_log_out:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getCategory(){
        SharedPreferences sharedPreferences=getSharedPreferences("LOGIN_DATA",MODE_PRIVATE);
        String apiKey=sharedPreferences.getString("API_KEY",null);
        String userId=sharedPreferences.getString("USER_ID",null);
        String url=EndPoint.getCategory(apiKey,userId);
        Log.d("data","the url is:"+url);
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        RecyclerView recyclerView;
                        RecyclerView.LayoutManager layoutManager;
                        HomeActivityRecyclerViewAdapter recyclerViewAdapterAdapter;
                        ArrayList<Category> arrayList=new ArrayList<>();

                        recyclerView=findViewById(R.id.home_activity_recycler_view);
                        layoutManager=new LinearLayoutManager(HomeActivity.this);


                        Log.d("data","the response is:"+response.toString());

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        CategoryList list = gson.fromJson(response.toString(), CategoryList.class);

                        arrayList=list.getArrayList();
                         Log.d("data", ""+list.getArrayList().size());
                         

                        recyclerViewAdapterAdapter=new HomeActivityRecyclerViewAdapter(HomeActivity.this,arrayList);
                        recyclerView.setAdapter(recyclerViewAdapterAdapter);
                        recyclerView.setLayoutManager(layoutManager);

                        init();


//                        Log.d("data","arrayList is:"+list.getArrayList().get(0).getCname());
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
