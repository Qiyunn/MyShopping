package com.example.myshopping.fragments;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.activities.CheckOutActivity;
import com.example.myshopping.activities.HomeActivity;
import com.example.myshopping.adapters.CartRecyclerViewAdapter;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.databases.DBHelper;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.Cart;
import com.example.myshopping.models.Coupon;
import com.example.myshopping.models.CouponList;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalCartFragment extends Fragment implements CartRecyclerViewAdapter.ClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CartRecyclerViewAdapter adapter;
    ArrayList<Cart> arrayList = new ArrayList<>();
    DBHelper dbHelper;

    TextView textViewAmount, textViewTotalPrice;
    Button buttonGoShop, buttonCheckOut;

    FragmentManager fragmentManager;

    EditText editTextCoupon;
    Button buttonCouponApply;
    TextView textViewAfterCoupon;

    Handler handler = new Handler();

    public NormalCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_normal_cart, container, false);
        fragmentManager=getActivity().getSupportFragmentManager();
        recyclerView = view.findViewById(R.id.normal_cart_recycler_view);
        textViewAmount = view.findViewById(R.id.normal_cart_text_amount);
        textViewTotalPrice = view.findViewById(R.id.normal_cart_text_total_price);
        buttonCheckOut = view.findViewById(R.id.normal_cart_button_check);
        buttonGoShop = view.findViewById(R.id.normal_cart_button_shop);

         editTextCoupon=view.findViewById(R.id.coupon_edit_text);
         buttonCouponApply=view.findViewById(R.id.coupon_apply_button);
         textViewAfterCoupon=view.findViewById(R.id.text_after_coupon);

        buttonCouponApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHelper userHelper=new UserHelper();
                User user1=userHelper.returnUser();
                returnCoupon(user1, editTextCoupon.getText().toString());
//                textViewTotalPrice.setPaintFlags(textViewTotalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        setUpClick();
        setUpRecyclerView();
        setUpText();
        return view;
    }

    public interface GetDiscount{
        public void getResponse(int discount);
    }

    public void returnCoupon(User user,String coupon){

        String url= EndPoint.returnCoupon(user,coupon);
        Log.d("hi","the url is:"+url);
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("hi",response.toString());
                        GsonBuilder gsonBuilder=new GsonBuilder();
                        Gson gson=gsonBuilder.create();
                        CouponList list=gson.fromJson(response,CouponList.class);
                        Log.d("hi","the list is"+list);
                        if(list!=null) {
                            ArrayList<Coupon> arrayList = list.getArrayList();
                            Coupon coupon1 = arrayList.get(0);
                            final int discount = Integer.parseInt(coupon1.getDiscount());

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewTotalPrice.setText(String.valueOf(dbHelper.getPrice()- discount));
                                }
                            });
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textViewTotalPrice.setText(String.valueOf(dbHelper.getPrice()-discount));
//                                }
//                            });


//                            Log.d("hi","the discount is:"+discount);
//                            getDiscount.getResponse(discount);
//                            textViewTotalPrice.setText(String.valueOf(Integer.parseInt(textViewTotalPrice.getText().toString())- discount));
                        }else{
                            Log.d("hi","The coupon is not valid!");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("hi",error.getMessage());
                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);


    }

    private void setUpClick() {
        buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckOutActivity.class));
            }
        });

        buttonGoShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });
    }


    private void setUpRecyclerView() {
        dbHelper = new DBHelper(getContext());

        arrayList = dbHelper.selectFromCart();
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new CartRecyclerViewAdapter(getContext(), arrayList);
        adapter.setClickListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setUpText() {


        textViewAmount.setText("Total Amount: " + dbHelper.getAmount());
        textViewTotalPrice.setText("Total Price: $" + dbHelper.getPrice());
    }


    @Override
    public void itemClicked(View v, int position) {
        Cart cart = new Cart();
        cart = arrayList.get(position);

        switch (v.getId()) {
            case R.id.normal_cart_button_delete:
                dbHelper.deleteCart(cart);
                arrayList.remove(position);
                adapter.setData(arrayList);
                setUpText();
                if(dbHelper.selectFromCart().size()==0){
                    fragmentManager.beginTransaction().replace(R.id.cart_activity_frame_layout,new EmptyCartFragment()).commit();
                }
                break;

            case R.id.normal_cart_button_add:
                dbHelper.addQuantity(cart);
                cart.setItemQuantity(cart.getItemQuantity()+1);
                adapter.setData(arrayList);
                setUpText();
                break;

            case R.id.normal_cart_button_minus:
                dbHelper.minusQuantity(cart);
                if(cart.getItemQuantity()!=1){
                cart.setItemQuantity(cart.getItemQuantity()-1);
                    adapter.setData(arrayList);
                }
                setUpText();
                break;

        }
    }
}
