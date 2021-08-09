package com.example.clicknbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clickbuy.R;

public class adminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnAddProduct = findViewById(R.id.buttonAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchAddProduct = new Intent(adminActivity.this, adminAddProducts.class);
                startActivity(launchAddProduct);
                finish();
            }
        });

//        Button btnShowProducts = findViewById(R.id.buttonShowProduct);
//        btnShowProducts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent launchShowProducts = new Intent(adminActivity.this, adminShowProducts.class);
//                startActivity(launchShowProducts);
//                finish();
//            }
//        });


    }
}