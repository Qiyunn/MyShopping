package com.example.myshopping.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myshopping.app.MyApplication;

public class VolleySingleton {

    private static VolleySingleton mInstance=null;
    private RequestQueue requestQueue;

    private VolleySingleton(){
        requestQueue= Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getInstance(){
        if(mInstance==null){
            mInstance=new VolleySingleton();
        }
        return mInstance;
    }

    public  RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }


}
