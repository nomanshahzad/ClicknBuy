package com.example.clicknbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clickbuy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        Button SignUp = findViewById(R.id.btnSignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupUser();

            }
        });

        TextView Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent launchLogin = new Intent(signupActivity.this, loginActivity.class);
                startActivity(launchLogin);
                finish();
            }
        });
    }

    private void signupUser() {
        TextView temail, tpassword, tconfirmPassword;
        temail = findViewById(R.id.etEmailAddress);
        String email = temail.getText().toString();
        tpassword = findViewById(R.id.etPassword);
        String password = tpassword.getText().toString();
        tconfirmPassword = findViewById(R.id.etConfirmPassword);
        String confirmPassword = tpassword.getText().toString();



        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Email & Password can't be blank", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signupActivity.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            updateUI(user);
//
//                            Intent launchmain = new Intent(signupActivity.this, MainActivity.class);
//                            startActivity(launchmain);
//                            finish();

                        } else {
                            Log.w("Error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signupActivity.this, "Account not created", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
//    private void updateUI(Object o) {
//
//    }
}