package com.example.clicknbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clickbuy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userShowProducts extends AppCompatActivity {
    Button buybtn;

    private RecyclerView recyclerView;
    //    private ArrayList<Model> list;
    private ArrayList<Product> list;

//    ArrayList<String> key=new ArrayList<>();

    private UserShowProductAdapter adapter;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Product");
//    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show_products);



        recyclerView = findViewById(R.id.userrecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<>();
        adapter = new UserShowProductAdapter(this,list);
        recyclerView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Product product = dataSnapshot.getValue(Product.class);
                    list.add(product);
//                    key.add(dataSnapshot.getKey());
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent launchadmin = new Intent(userShowProducts.this, userActivity.class);
        startActivity(launchadmin);
        finish();
    }
}