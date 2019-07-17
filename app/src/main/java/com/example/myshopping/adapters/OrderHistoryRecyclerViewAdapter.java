package com.example.myshopping.adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myshopping.R;
import com.example.myshopping.app.Config;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.OrderHistory;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;

import java.util.ArrayList;

public class OrderHistoryRecyclerViewAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<OrderHistory> arrayList=new ArrayList<>();
    UserHelper userHelper;

    public OrderHistoryRecyclerViewAdapter(Context context, ArrayList<OrderHistory> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        userHelper=new UserHelper();
    }

    public void setData(ArrayList arrayList){
        this.arrayList=arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_order_history_recycler_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
       OrderHistory orderHistory=new OrderHistory();
       orderHistory=arrayList.get(i);

       myViewHolder.textViewOrderId.setText("Order Id is:"+orderHistory.getOrderid());
       myViewHolder.textViewItemName.setText("Item Name is:"+orderHistory.getItemname());
       myViewHolder.textViewItemQuntity.setText("Item Quantity is:"+orderHistory.getItemquantity());
       myViewHolder.textViewPaidPrice.setText("Total Paid Price is:"+orderHistory.getPaidprice());
       myViewHolder.textViewPlacedOn.setText("Placed Time is:"+orderHistory.getPlacedon());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewOrderId,textViewItemName,textViewItemQuntity,textViewPaidPrice,textViewPlacedOn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderId=itemView.findViewById(R.id.order_history_text_order_id);
            textViewItemName=itemView.findViewById(R.id.order_history_text_item_name);
            textViewItemQuntity=itemView.findViewById(R.id.order_history_text_item_quantity);
            textViewPaidPrice=itemView.findViewById(R.id.order_history_text_paid_price);
            textViewPlacedOn=itemView.findViewById(R.id.order_history_text_placed_on);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OrderHistory orderHistory=arrayList.get(getAdapterPosition());
            User user=userHelper.returnUser();
            getTrack(user,orderHistory.getOrderid());
        }
    }

    public void getTrack(User user,String orderId){
//        user=userHelper.returnUser();
        String url= EndPoint.getTrackStatus(user,orderId);
        StringRequest request=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("track",response.toString());
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("track",error.getMessage());
                    }
                });
        VolleySingleton.getInstance().addToRequestQue(request);
    }
}
