package com.example.myshopping.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myshopping.fragments.productViewFragment;

import java.util.ArrayList;

public class ProductFragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> arrayList=new ArrayList<>();

    public ProductFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public void addFragment(String title){
        arrayList.add(productViewFragment.newInstance(title));
    }
}
