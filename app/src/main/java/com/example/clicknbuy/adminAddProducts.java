package com.example.clicknbuy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clickbuy.R;
import com.example.clicknbuy.Model;
import com.example.clicknbuy.Product;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class adminAddProducts extends AppCompatActivity {
    Model model ;
    Button uploadButton,addButton;
    ImageView image;
    TextView productName,price;
    ProgressBar progressBar;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);


        uploadButton = findViewById(R.id.uploadImage);
        addButton = findViewById(R.id.addProduct);
        image = findViewById(R.id.addImage);
        productName = findViewById(R.id.productName);
        price = findViewById(R.id.price);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,2);

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri!=null){
                    uploadToFirebase(imageUri);
                }
                else{
                    Toast.makeText(adminAddProducts.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addProduct();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    private void uploadToFirebase(Uri uri){
        StorageReference fileRef = reference.child(System.currentTimeMillis()+"."+getFileExtention(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        model = new Model((uri.toString()));
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                        progressBar.setVisibility(View.INVISIBLE);



//                        Product product = new Product(uri.toString());
//                        String productId = root.push().getKey();
//                        root.child(productId).setValue(product);
                        Toast.makeText(adminAddProducts.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(adminAddProducts.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtention(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    private void addProduct(){
//        Model model;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Product");


                Product product = new Product(productName.getText().toString(),price.getText().toString(),model.getImageUrl());
                myRef.push().setValue(product);
                Intent launchAdminActivity = new Intent(adminAddProducts.this, adminActivity.class);
                startActivity(launchAdminActivity);
                finish();
            }
        });
    }
}