package com.example.e_learning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.databinding.ActivityAttandBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AttandActivity extends AppCompatActivity {
ActivityAttandBinding binding;
DatabaseReference reference;
FirebaseDatabase database;
 public static    String attendCode;
  public static   String courseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAttandBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courseId=getIntent().getStringExtra("CourseId");

        binding.code.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
               RandomNumber();
            }
        });
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendCodeToReaLTime(courseId);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AttandActivity.this, ControlCourse.class));
            }
        });

    }

    private void RandomNumber(){
        Random random=new Random();
        int value=random.nextInt(9999-1000)+1000;
        binding.textAttend.setText(Integer.toString(value));
    }
    private void SendCodeToReaLTime(String courseId){
        database= FirebaseDatabase.getInstance();
        attendCode=binding.textAttend.getText().toString();
      database.getReference().child("Attendance").child(courseId).child(attendCode).setValue("");

    }

}