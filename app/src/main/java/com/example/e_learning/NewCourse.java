package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.Courses;
import com.example.e_learning.databinding.ActivityNewCourseBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewCourse extends AppCompatActivity {
ActivityNewCourseBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;

   DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
   ArrayList<Courses> courses_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNewCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        courses_list=new ArrayList<>();
        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName=binding.CourseName.getText().toString();
                String gradeQuze=binding.GradeQuze.getText().toString();
                String proGrade=binding.projectGrade.getText().toString();
                String attGrade=binding.AttandGrade.getText().toString();
               ValidCourse(courseName,gradeQuze,proGrade,attGrade);
           }
        });

        binding.arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewCourse.this, Doctor.class));
            }
        });
    }

    private void ValidCourse( String courseName,String gradeQuze,String  projectGrade,String attandGrade){
        if (courseName.length()>0&&gradeQuze.length()>0&&projectGrade.length()>0&&attandGrade.length()>0) {
            binding.CourseName.setText("");
            binding.GradeQuze.setText("");
            binding.projectGrade.setText("");
          binding.AttandGrade.setText("");
          ConvertToInt(courseName,gradeQuze,projectGrade,attandGrade);
        }
          else if (courseName==null||courseName.length()<4){
                binding.CourseName.setText("");
            Toast.makeText(NewCourse.this, "Please enter valid course", Toast.LENGTH_SHORT).show();
        } else if (gradeQuze.equals("0")||gradeQuze.isEmpty()) {
                binding.GradeQuze.setText("");
                Toast.makeText(NewCourse.this, "Please enter valid grade Quiz", Toast.LENGTH_SHORT).show();
        } else if (projectGrade.isEmpty()||projectGrade.equals("0")) {
                binding.projectGrade.setText("");
                Toast.makeText(NewCourse.this, "Please enter valid projectGrade", Toast.LENGTH_SHORT).show();
            } else if (attandGrade.isEmpty()||attandGrade.equals("0")) {
                binding.AttandGrade.setText("");
                Toast.makeText(NewCourse.this, "Please enter valid  Attend Grade", Toast.LENGTH_SHORT).show();
            }


    }
    private void SendCourseToRealTime(String courseName,int gradeQuze,int projectGrade,int attandGrade){
      String id =reference.push().getKey();
        Courses courses=new Courses(courseName,id,FirebaseAuth.getInstance().getUid(),attandGrade,gradeQuze, projectGrade);
        database.getReference().child("Courses").child(id).setValue(courses);

    }
    private void ConvertToInt(String courseName,String gradeQuze,String  proGrade,String attGrade){
        int gradeQuiz= Integer.parseInt(gradeQuze);
        int projectGrade= Integer.parseInt(proGrade);
        int attendGrade= Integer.parseInt(attGrade);
        SendCourseToRealTime(courseName,gradeQuiz,projectGrade,attendGrade);
    }

}