package com.example.myshopping.activities;

import android.content.Intent;
import android.os.Build;
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
import com.example.myshopping.databases.DBHelper;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.Cart;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    Toolbar toolbar;

    EditText editTextBillAdd,editTextDeliAdd;
    Button buttonPlaceOrder;

    DBHelper dbHelper;
    User user;
    UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        dbHelper=new DBHelper(this);

        init();
    }

    private void init() {
        setUpToolBar();
        setUpView();
    }

    private void setUpView() {
        editTextBillAdd=findViewById(R.id.check_out_edit_billadd);
        editTextDeliAdd=findViewById(R.id.check_out_edit_deliveryadd);
        buttonPlaceOrder=findViewById(R.id.check_out_button_place_order);

        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHelper=new UserHelper();
                user=userHelper.returnUser();
                ArrayList<Cart> arrayList=new ArrayList<>();
                arrayList=dbHelper.selectFromCart();
                for(int i=0;i<arrayList.size();i++){
                    Cart cart=arrayList.get(i);
                    postOrder(cart,user,editTextBillAdd.getText().toString(),editTextDeliAdd.getText().toString());
                }
                dbHelper.deletTable();
                startActivity(new Intent(CheckOutActivity.this,ThanksActivity.class));




            }
        });
    }

    private void setUpToolBar() {
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Check Out");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void postOrder(Cart cart, User user,String billadd,String deliveryadd){
        String url= EndPoint.postOrder(cart,user,billadd,deliveryadd);
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("check_out","respose is: "+response.toString());
                        Toast.makeText(CheckOutActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CheckOutActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);
    }

}
