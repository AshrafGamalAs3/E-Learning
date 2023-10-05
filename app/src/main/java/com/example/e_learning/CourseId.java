package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_learning.databinding.ActivityCourseIdBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CourseId extends AppCompatActivity {
    ActivityCourseIdBinding binding;
    ArrayList<Courses> Course_List;
    RecyclerView recyclerView;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Courses");
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
 String courseId;

 String name;

 int i=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            courseId = binding.courseid.getText().toString();
                addCourse();
                ValidCourseId(courseId);
                binding.courseid.setText("");


            }
        });
    reference1.child("Users").child(FirebaseAuth.getInstance().getUid()).orderByChild(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                name=String.valueOf(snapshot.child("name").getValue());
           }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });




        binding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseId.this, StudentORInstractor.class));
            }
        });


    }
    private void ValidCourseId(String courseId) {
        if (courseId.equals("")) {
            Toast.makeText(CourseId.this, "Please enter course id", Toast.LENGTH_LONG).show();
        } else {
            CheckCourse(courseId);
        }

    }
    private void CheckCourse(String courseId) {
        String studentId = FirebaseAuth.getInstance().getUid();
        reference.child(courseId).child("courseName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot!=null){
                  String  courseName=snapshot.getValue(String.class);
                    AddStudentToCourse(studentId,courseName,courseId);
                    AddStudentToRealtimeDatabase(courseId,studentId,courseName);
                    Toast.makeText(CourseId.this,"Course added",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(CourseId.this,"Course not found",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




   }

    private void AddStudentToRealtimeDatabase(String courseId, String studentId,String courseName) {
        Students students = new Students(studentId, courseId,name,courseName, 0, 0, 0);
        reference1.child("Students").child(studentId).child(courseId).setValue(students);

    }
    private void  AddStudentToCourse(String studentId,String courseName,String courseId){
        Students students = new Students(studentId, courseId,name,courseName,0, 0, 0);
        reference.child(courseId).child("Student").child(studentId).setValue(students);
    }
private  void addCourse(){
    String studentId = FirebaseAuth.getInstance().getUid();
    recyclerView = findViewById(R.id.recycler_student);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);
    Course_List = new ArrayList<>();
    AdapterStudent adapterStudent = new AdapterStudent(Course_List, this);
    recyclerView.setAdapter(adapterStudent);
    reference1.child("Students").child(studentId).addValueEventListener(new ValueEventListener() {
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

}


}