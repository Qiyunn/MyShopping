package com.example.myshopping.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;
import com.example.myshopping.databases.DBHelper;
import com.example.myshopping.models.Cart;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Cart> arrayList=new ArrayList<>();
    DBHelper dbHelper;
    ClickListener clickLitener;

    public CartRecyclerViewAdapter(Context context, ArrayList<Cart> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        dbHelper=new DBHelper(context);
    }

    public void setData(ArrayList<Cart> arrayList){
        this.arrayList=arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.row_cart_recycler_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Cart cart=new Cart();
        cart=arrayList.get(i);
        Glide.with(context).load(cart.getImage()).into(myViewHolder.imageView);
        myViewHolder.textViewName.setText(cart.getItemName());
        myViewHolder.textViewQuantity.setText(String.valueOf(cart.getItemQuantity()));
        myViewHolder.textViewPrice.setText("$"+String.valueOf(cart.getItemQuantity()*Integer.parseInt(cart.getItemPrice())));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewName,textViewPrice,textViewQuantity;
        Button buttonAdd,buttonMinus,buttonDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.normal_cart_image_view);
            textViewName=itemView.findViewById(R.id.normal_cart_text_name);
            textViewPrice=itemView.findViewById(R.id.normal_cart_text_price);
            textViewQuantity=itemView.findViewById(R.id.normal_cart_text_quantity);

            buttonAdd=itemView.findViewById(R.id.normal_cart_button_add);
            buttonMinus=itemView.findViewById(R.id.normal_cart_button_minus);
            buttonDelete=itemView.findViewById(R.id.normal_cart_button_delete);

            buttonAdd.setOnClickListener(this);
            buttonMinus.setOnClickListener(this);
            buttonDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickLitener.itemClicked(v,getAdapterPosition());
        }
    }

    public void setClickListener(ClickListener clickLitener){
        this.clickLitener=clickLitener;
    }

    public interface ClickListener{
        void itemClicked(View v,int position);
    }
}
