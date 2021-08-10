package com.example.clicknbuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clickbuy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserShowProductAdapter extends RecyclerView.Adapter<UserShowProductAdapter.MyViewHolder> {
    //    private ArrayList<Model> mlist;
    private ArrayList<Product> mlist;
//    private ArrayList<String> key;
    private Context context;

    public UserShowProductAdapter() {
    }

    public UserShowProductAdapter(Context context,ArrayList<Product> mlist) {
        this.mlist = mlist;
//        this.key = key;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_product_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = mlist.get(position);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(product.getPrice());
        Glide.with(context).load(mlist.get(position).getImageUrl()).into(holder.imageView);
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Product");
//                db.child(key.get(position)).removeValue();
//                ((adminShowProductActivity)context).recreate();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView,buy;
        TextView productName,productPrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.usershowImage);
            productName = itemView.findViewById(R.id.usershowProductName);
            productPrice = itemView.findViewById(R.id.usershowProductPrice);
//            buy = itemView.findViewById(R.id.buybutton);
        }
    }
}

