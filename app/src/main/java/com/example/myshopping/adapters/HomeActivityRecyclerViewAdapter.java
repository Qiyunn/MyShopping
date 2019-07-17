package com.example.myshopping.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.myshopping.R;
import com.example.myshopping.activities.SubCategoryActivity;
import com.example.myshopping.app.EndPoint;
import com.example.myshopping.helpers.UserHelper;
import com.example.myshopping.models.Category;
import com.example.myshopping.models.SubCategory;
import com.example.myshopping.models.SubCategoryList;
import com.example.myshopping.models.User;
import com.example.myshopping.network.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class HomeActivityRecyclerViewAdapter extends RecyclerView.Adapter<HomeActivityRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Category> arrayList=new ArrayList<>();

    public HomeActivityRecyclerViewAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setData(ArrayList<Category> arrayList){
        this.arrayList=arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)  {
        View view= LayoutInflater.from(context).inflate(R.layout.row_home_recycler_view,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Category category=new Category();
        category=arrayList.get(i);

        Glide.with(context).load(category.getCimagerl()).into(myViewHolder.imageView);
        myViewHolder.textViewName.setText(category.getCname());
//        myViewHolder.textViewDescription.setText(category.getCdiscription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.category_image_view);
            textViewName=itemView.findViewById(R.id.category_text_cname);
//            textViewDescription=itemView.findViewById(R.id.category_text_cdescription);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//               context.startActivity(new Intent(context,SubCategoryActivity.class));
            getSubCategory();
        }


        public void getSubCategory(){
            UserHelper userHelper=new UserHelper();
            User user=userHelper.returnUser();
            final String id=arrayList.get(getAdapterPosition()).getCid();
            String url= EndPoint.getSubCategory(id,user);
            Log.d("subcate","the url is:"+url);
            StringRequest request=new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("subcate","response is:"+response);

                            GsonBuilder gsonBuilder=new GsonBuilder();
                            Gson gson=gsonBuilder.create();
                            SubCategoryList list=new SubCategoryList();

                            list=gson.fromJson(response.toString(),SubCategoryList.class);

                            Log.d("subcate","list is:"+list);

//                            SubCategory subCategory=list.getArrayList().get(0);

 //                            Log.d("subcate","the  subcategory is :"+subCategory.getScname());


                            Intent intent=new Intent(context, SubCategoryActivity.class);
                            intent.putExtra("subcategory",list);
                            intent.putExtra("ID",id);
                            context.startActivity(intent);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("subcate",error.getMessage());
                        }
                    });
            VolleySingleton.getInstance().addToRequestQue(request);
        }
    }
}
