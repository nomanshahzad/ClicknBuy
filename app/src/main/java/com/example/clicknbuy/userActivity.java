package com.example.clicknbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clickbuy.R;


public class userActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button buybtn = findViewById(R.id.buttonBuyProduct);
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchpage = new Intent(userActivity.this, userShowProducts.class);
                startActivity(launchpage);
                finish();
            }
        });

        Button logout = findViewById(R.id.logoutUser);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchShowProducts = new Intent(userActivity.this, loginActivity.class);
                startActivity(launchShowProducts);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent launchadmin = new Intent(userActivity.this, loginActivity.class);
        startActivity(launchadmin);
        finish();
    }
}