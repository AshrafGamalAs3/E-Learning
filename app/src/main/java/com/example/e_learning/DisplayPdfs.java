package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.e_learning.databinding.ActivityDisplayPdfsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayPdfs extends AppCompatActivity {
    ActivityDisplayPdfsBinding binding;
    String courseId;
    DatabaseReference reference1;
    ArrayList<String> material_list=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayPdfsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courseId = getIntent().getStringExtra("CourseId");
        reference1 = FirebaseDatabase.getInstance().getReference("Courses Materials");
        AdapterPdf adapterPdf = new AdapterPdf(material_list,this);
        reference1.child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                material_list.clear();
                if (snapshot.getValue() != null) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        material_list.add(snapshot1.getValue(String.class));
                        adapterPdf.setList(material_list);
                        binding.recyclerPdf.setAdapter(adapterPdf);
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayPdfs.this, CourseId.class));
            }
        });

    }
    }