package com.example.chatapp.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chatapp.R;
import com.example.chatapp.databinding.ActivitySignInBinding;
import com.google.common.base.MoreObjects;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();


    }
    private void setListener(){
        binding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
                binding.buttonSignIn.setOnClickListener(v -> addDataToFirestore());
    }
    private void addDataToFirestore(){
        FirebaseFirestore db  = FirebaseFirestore.getInstance();
        HashMap<String, Object> data = new HashMap<>();
        data.put("first_name", "Gurvinder");
        data.put("last_name", "Yadav");
        db.collection("users")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Data Inserted succesfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception ->{
                    Toast.makeText(this, "Data Could not be inserted", Toast.LENGTH_SHORT).show();
                });
    }
}