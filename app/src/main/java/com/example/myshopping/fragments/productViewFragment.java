package com.example.myshopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myshopping.R;
import com.example.myshopping.models.SampleData;

/**
 * A simple {@link Fragment} subclass.
 */
public class productViewFragment extends Fragment {

   ImageView imageView;

    public productViewFragment() {
        // Required empty public constructor
    }

    public static productViewFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("TITLE",title);
        productViewFragment fragment = new productViewFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_view, container, false);
        imageView=view.findViewById(R.id.prodect_viewpager_imageview);

        String title= getArguments().getString("TITLE");
        int id=SampleData.getData(title);
        imageView.setImageResource(id);
        return view;
    }



}
