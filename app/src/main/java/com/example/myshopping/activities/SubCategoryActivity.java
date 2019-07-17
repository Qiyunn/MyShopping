package com.example.myshopping.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myshopping.R;
import com.example.myshopping.adapters.SubCateRecyclerViewAdapter;
import com.example.myshopping.adapters.SubCateViewPagerAdapter;
import com.example.myshopping.models.SubCategory;
import com.example.myshopping.models.SubCategoryList;

import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    SubCateViewPagerAdapter viewPagerAdapter;
    Toolbar toolbar;

    SubCategoryList list;
    ArrayList<SubCategory> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        toolbar=findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subcategory");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    private void init() {
        tabLayout=findViewById(R.id.subcate_tablayout);
        viewPager=findViewById(R.id.subcate_view_pager);
        viewPagerAdapter=new SubCateViewPagerAdapter(getSupportFragmentManager());

        list=(SubCategoryList) getIntent().getSerializableExtra("subcategory");
        String cid=getIntent().getExtras().getString("ID");
        if(list!=null){
        arrayList=list.getArrayList();
        }

        for(int i=0;i<arrayList.size();i++){
            viewPagerAdapter.addFragment(cid,arrayList.get(i).getScid(),arrayList.get(i).getScname());
        }

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

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
                startActivity(new Intent(SubCategoryActivity.this,CartActivity.class));
                break;
            case R.id.main_menu_item_search:
                break;
        }
        return true;
    }

}
