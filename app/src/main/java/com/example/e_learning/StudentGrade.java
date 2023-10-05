package com.example.e_learning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learning.AdapterShowStudent;
import com.example.e_learning.Students;
import com.example.e_learning.databinding.ActivityStudentGradeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentGrade extends AppCompatActivity {

    DatabaseReference reference;
    ActivityStudentGradeBinding binding;
   public static String courseId;
    ArrayList<Students> student_list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentGradeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reference= FirebaseDatabase.getInstance().getReference("Students");
        courseId=getIntent().getStringExtra("CourseId");
        recyclerView =binding.recyclerStudentGrade;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        student_list=new ArrayList<>();
        AdapterShowStudent adapterShowStudent=new AdapterShowStudent(student_list,getApplicationContext());
        recyclerView.setAdapter(adapterShowStudent);
        reference.child(FirebaseAuth.getInstance().getUid()).child(courseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                student_list.clear();
                    Students student=snapshot.getValue(Students.class);
                    student_list.add(student);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}