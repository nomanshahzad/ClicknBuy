package com.example.clicknbuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clickbuy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        Button Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });


        TextView signUp = findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent launchSignUp= new Intent(loginActivity.this,signupActivity.class);
                startActivity(launchSignUp);
                finish();
            }
        });
    }


    private void Login() {
        TextView temail, tpassword, tconfirmPassword;
        temail = findViewById(R.id.etEmailAddress);
        String email = temail.getText().toString();
        tpassword = findViewById(R.id.etPassword);
        String password = tpassword.getText().toString();


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email & Password can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.equals("admin@gmail.com") && password.equals("admin")) {
            Intent launchadmin = new Intent(loginActivity.this, adminActivity.class);
            startActivity(launchadmin);
            finish();
            return;
        }
        else{

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent launchUser = new Intent(loginActivity.this, userActivity.class);
                            startActivity(launchUser);
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    }
}