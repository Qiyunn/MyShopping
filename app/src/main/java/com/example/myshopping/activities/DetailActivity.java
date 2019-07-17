package com.example.myshopping.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;
import com.example.myshopping.databases.DBHelper;
import com.example.myshopping.models.Product;
import com.example.myshopping.models.SubCategory;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ImageView imageView;
    TextView textViewName,textViewDescrip,textViewPrice;

    Product product;

    Button buttonAdd,buttonBuy;

    DBHelper dbHelper;

    Spinner spinner;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper=new DBHelper(this);
        init();
    }

    private void init() {

        setUpSpinner();
        setUpView();
        setUpToolbar();


    }

    private void setUpSpinner() {
        spinner=findViewById(R.id.detail_activity_spinner);
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        ArrayAdapter<Integer> adapter= new ArrayAdapter<>(DetailActivity.this,android.R.layout.simple_spinner_item,arrayList);

        spinner.setAdapter(adapter);
    }

    private void setUpView() {
        imageView=findViewById(R.id.detail_image_view);
        textViewName=findViewById(R.id.detail_text_name);
        textViewDescrip=findViewById(R.id.detail_text_description);
        textViewPrice=findViewById(R.id.detail_text_price);

        buttonAdd=findViewById(R.id.detail_button_add);
        buttonBuy=findViewById(R.id.detail_button_buy);

        product=(Product) getIntent().getSerializableExtra("PRODUCT");

        Glide.with(this).load(product.getImage()).into(imageView);
        textViewName.setText(product.getPname());
        textViewDescrip.setText(product.getDiscription());
        textViewPrice.setText("$"+product.getPrize());

        buttonAdd.setOnClickListener(this);
        buttonBuy.setOnClickListener(this);
    }

    private void setUpToolbar() {
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle(product.getPname());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.main_menu_item_cart:
                startActivity(new Intent(DetailActivity.this,CartActivity.class));
                break;
            case R.id.main_menu_item_search:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_button_add:
                count=Integer.parseInt(spinner.getSelectedItem().toString());
                dbHelper.insertToCart(product,count);
                break;
            case R.id.detail_button_buy:
                break;
        }

    }
}
