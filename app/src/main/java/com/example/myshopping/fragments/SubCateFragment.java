package com.example.myshopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.adapters.SubCateRecyclerViewAdapter;
import com.example.myshopping.adapters.SubCateViewPagerAdapter;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.Product;
import com.example.myshopping.models.ProductList;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCateFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SubCateRecyclerViewAdapter recyclerViewAdapter;

    ArrayList<Product> arrayList=new ArrayList<>();

    public SubCateFragment() {
        // Required empty public constructor
    }

    public static SubCateFragment newInstance(String cid,String sid,String title) {

        Bundle args = new Bundle();
        args.putString("TITLE",title);
        args.putString("CID",cid);
        args.putString("SID",sid);
        SubCateFragment fragment = new SubCateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sub_cate, container, false);

        String title= getArguments().getString("TITLE");
        String cid=getArguments().getString("CID");
        String sid=getArguments().getString("SID");
        init(view);

        getProductArrayList(cid,sid);

        return view;
    }

    private void getProductArrayList(String cid,String sid) {
        UserHelper userHelper=new UserHelper();
        User user=userHelper.returnUser();
        String url= EndPoint.getProductList(cid,sid,user);
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder=new GsonBuilder();
                        Gson gson=builder.create();
                        ProductList list=gson.fromJson(response.toString(),ProductList.class);
                        if(list!=null){
                        ArrayList<Product> list1=new ArrayList<>();
                        list1=list.getArrayList();
                        recyclerViewAdapter.setData(list1);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);

    }

    private void init(View view) {
        recyclerView=view.findViewById(R.id.subcate_recycler_view);
        layoutManager=new LinearLayoutManager(getContext());

        recyclerViewAdapter = new SubCateRecyclerViewAdapter(getContext(),arrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}
