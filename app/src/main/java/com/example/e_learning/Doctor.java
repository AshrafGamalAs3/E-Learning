package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_learning.databinding.ActivityDoctorBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctor extends AppCompatActivity {
ActivityDoctorBinding binding;
    ArrayList<Courses> Course_List;
    DatabaseReference reference;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView=findViewById(R.id.recycler);
        reference=FirebaseDatabase.getInstance().getReference("Courses");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Course_List=new ArrayList<>();

        AdapterCourse adapterCourse=new AdapterCourse(Course_List,this);
        recyclerView.setAdapter(adapterCourse);


        reference.orderByChild("instructorId").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Course_List.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Courses courses=dataSnapshot.getValue(Courses.class);
                    Course_List.add(courses);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor.this, StudentORInstractor.class));
            }
        });
        binding.addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor.this, NewCourse.class));
            }
        });
    }

}