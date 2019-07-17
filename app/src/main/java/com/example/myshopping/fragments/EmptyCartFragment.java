package com.example.myshopping.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myshopping.R;
import com.example.myshopping.activities.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmptyCartFragment extends Fragment {

    ImageView imageView;
    Button button;
    public EmptyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_empty_cart, container, false);
        init(view);

        return  view;
    }

    private void init(View view) {
        imageView=view.findViewById(R.id.empty_cart_image_view);
        button=view.findViewById(R.id.empty_cart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });

    }

}
