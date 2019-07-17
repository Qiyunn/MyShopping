package com.example.myshopping.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.adapters.OrderHistoryRecyclerViewAdapter;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.OrderHistory;
import com.example.myshopping.models.OrderHistoryList;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    OrderHistoryRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<OrderHistory> arrayList=new ArrayList<>();

    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        userHelper=new UserHelper();
        setUpToolBar();
        setUpRecyclerView();
        getHistoryOrder();
    }

    private void setUpRecyclerView() {
        recyclerView=findViewById(R.id.order_history_recycler_view);
        adapter=new OrderHistoryRecyclerViewAdapter(this,arrayList);
        layoutManager=new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setUpToolBar() {
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Order History");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void getHistoryOrder(){
        User user=userHelper.returnUser();
        String url= EndPoint.getOrderHistory(user);
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder=new GsonBuilder();
                        Gson gson=gsonBuilder.create();
                        OrderHistoryList list=gson.fromJson(response.toString(),OrderHistoryList.class);

                         adapter.setData(list.getArrayList());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);
    }

}
