package com.example.myshopping.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myshopping.fragments.SubCateFragment;

import java.util.ArrayList;

public class SubCateViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<SubCateFragment> arrayList=new ArrayList<>();
    ArrayList<String> titleList=new ArrayList<>();

    public SubCateViewPagerAdapter(FragmentManager fm) {
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

    public void addFragment(String cid,String sid,String title){
        arrayList.add(SubCateFragment.newInstance(cid,sid,title));
        titleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
