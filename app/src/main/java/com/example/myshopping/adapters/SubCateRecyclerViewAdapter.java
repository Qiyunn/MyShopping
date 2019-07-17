package com.example.myshopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;
import com.example.myshopping.activities.DetailActivity;
import com.example.myshopping.models.Product;

import java.util.ArrayList;

public class SubCateRecyclerViewAdapter extends RecyclerView.Adapter<SubCateRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> arrayList = new ArrayList<>();

    public SubCateRecyclerViewAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setData(ArrayList<Product> list){
        this.arrayList=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_subcate_recycler_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Product product = new Product();
        product = arrayList.get(i);
        Glide.with(context).load(product.getImage()).into(myViewHolder.imageView);
        myViewHolder.textViewName.setText(product.getPname());
        myViewHolder.textViewPrice.setText("$"+product.getPrize());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewName, textViewPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.subcate_image_view);
            textViewName = itemView.findViewById(R.id.subcate_text_name);
            textViewPrice = itemView.findViewById(R.id.subcate_text_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product product=arrayList.get(getAdapterPosition());
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("PRODUCT",product);
            context.startActivity(intent);
        }
    }
}
