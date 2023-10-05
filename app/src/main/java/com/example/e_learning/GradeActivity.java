package com.example.e_learning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learning.AdapterShowStudent;
import com.example.e_learning.Students;
import com.example.e_learning.databinding.ActivityGradeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GradeActivity extends AppCompatActivity {
ActivityGradeBinding binding;


 String courseId;

    DatabaseReference reference;
    DatabaseReference reference1;
    ArrayList<Students> student_list;

 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGradeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reference=FirebaseDatabase.getInstance().getReference("Courses");
        courseId=getIntent().getStringExtra("CourseId");

        RecyclerView recyclerView=findViewById(R.id.recycler_show_student);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        student_list=new ArrayList<>();
      AdapterShowStudent adapterShowStudent=new AdapterShowStudent(student_list,getApplicationContext());
       recyclerView.setAdapter(adapterShowStudent);
        reference.child(courseId).child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                student_list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                  Students student=dataSnapshot.getValue(Students.class);
                    student_list.add(student);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });














    }
}