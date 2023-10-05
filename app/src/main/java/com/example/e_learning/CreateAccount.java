package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.e_learning.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class CreateAccount extends AppCompatActivity {
    ActivityCreateAccountBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;


    SharedPreferences sharedPreferences;


    MutableLiveData<ArrayList<Users>> membersLiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.userName.getText().toString();

                String email=binding.email.getText().toString();
                String password=binding.password.getText().toString();
                Create(name,email,password);




            }
        });
        binding.Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this,SignUpActivity.class));
            }
        });

    }




    private void Create(String name, String email, String password) {
        if (password.length() <= 7) {
            Toast.makeText(CreateAccount.this, "Please enter your password more than 8 char", Toast.LENGTH_LONG).show();
        }  else {
            AuthUser(name, email, password);

        }

    }
    private void AuthUser(String name,String email,String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if (task.isSuccessful()){
               SendUserToRealTime(name,email,password);
           }else {
               Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
           }
            }
        });

    }

    private void SendUserToRealTime(String name,String email,String password){
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid());

        Users user = new Users(name, email, password, Const.userType,FirebaseAuth.getInstance().getUid());
        String id = auth.getUid();


        database.getReference().child("Users").child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
           if (Const.userType=="student"){

               startActivity(new Intent(CreateAccount.this, CourseId.class));

           } else if (Const.userType=="instructor") {

               startActivity(new Intent(CreateAccount.this, Doctor.class));

           }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateAccount.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}