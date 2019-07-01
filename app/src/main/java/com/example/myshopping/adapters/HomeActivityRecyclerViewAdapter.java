package com.example.myshopping.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshopping.R;
import com.example.myshopping.models.Category;

import java.util.ArrayList;

public class HomeActivityRecyclerViewAdapter extends RecyclerView.Adapter<HomeActivityRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Category> arrayList=new ArrayList<>();

    public HomeActivityRecyclerViewAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_home_recycler_view,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Category category=new Category();
        category=arrayList.get(i);

        Glide.with(context).load(category.getCimagerl()).into(myViewHolder.imageView);
        myViewHolder.textViewName.setText(category.getCname());
        myViewHolder.textViewDescription.setText(category.getCdiscription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewName,textViewDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.category_image_view);
            textViewName=itemView.findViewById(R.id.category_text_cname);
            textViewDescription=itemView.findViewById(R.id.category_text_cdescription);
        }
    }
}
