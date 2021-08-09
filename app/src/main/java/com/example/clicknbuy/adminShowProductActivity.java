package com.example.clicknbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.clickbuy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminShowProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
//    private ArrayList<Model> list;
    private ArrayList<Product> list;
    ArrayList<String> key=new ArrayList<>();

    private ModelAdaper adapter;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Product");
//    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_product);

        recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ModelAdaper(this,list,key);
        recyclerView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Product product = dataSnapshot.getValue(Product.class);
                    list.add(product);
                    key.add(dataSnapshot.getKey());
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
}