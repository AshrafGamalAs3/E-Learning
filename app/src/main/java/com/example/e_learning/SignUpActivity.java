package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.e_learning.Const;
import com.example.e_learning.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.email.getText().toString();
                String password=binding.password.getText().toString();
                vaildate(email,password);
            }
        });
        
        
    }
    private void vaildate(String email, String password) {
        if (email.isEmpty() ) {
            Toast.makeText(SignUpActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
           
        } else if (password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else if (password.length() <= 8) {
            Toast.makeText(SignUpActivity.this, "your password should be more than 8 char", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (Const.userType=="student"){
                                    startActivity(new Intent(SignUpActivity.this, CourseId.class));

                                } else if (Const.userType=="instructor") {
                                    startActivity(new Intent(SignUpActivity.this, Doctor.class));

                                }
                            }
                        },1000);
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}