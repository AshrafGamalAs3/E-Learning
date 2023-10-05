package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.databinding.ActivityStudentControlBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentControlActivity extends AppCompatActivity {
ActivityStudentControlBinding binding;
public static String courseName;
public static String courseId;
public static String attendCode;
    public String doctorId;

DatabaseReference reference;
DatabaseReference reference1;
DatabaseReference reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courseId=getIntent().getStringExtra("courseId");
        courseName=getIntent().getStringExtra("courseName");
        doctorId=getIntent().getStringExtra("doctorId");

        binding.text.setText(courseName);

        reference= FirebaseDatabase.getInstance().getReference("Attendance");
        reference1= FirebaseDatabase.getInstance().getReference("Students");
        reference2= FirebaseDatabase.getInstance().getReference("Courses");
        binding.Activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.textCode.getText().toString().isEmpty()){
                    Toast.makeText(StudentControlActivity.this,"Please enter Attend Code",Toast.LENGTH_LONG).show();
                }  else {
                    attendCode=binding.textCode.getText().toString();
                    CheckAttendCode(attendCode);
                    binding.textCode.setText("");
                }

            }
        });
        binding.matrialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentControlActivity.this, DisplayPdfs.class);
                intent.putExtra("CourseId",courseId);
                startActivity(intent);
            }
        });
        binding.chatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentControlActivity.this, ChatActivity.class);
                intent.putExtra("CourseId",courseId);
                intent.putExtra("courseName",courseName);
                intent.putExtra("doctorID",doctorId);
                startActivity(intent);
            }
        });


      binding.imageQuze.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(StudentControlActivity.this, StudentQuizActivity.class);
              intent.putExtra("CourseId",courseId);
              startActivity(intent);
          }
      });
      binding.GradeImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(StudentControlActivity.this, StudentGrade.class);
              intent.putExtra("CourseId",courseId);
              startActivity(intent);
          }
      });


    }
    private void CheckAttendCode(String attendCode){
        reference.child(courseId).child("attendCode").equalTo(attendCode)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              ActivityAttend(attendCode);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private  void ActivityAttend(String attendCode){


        reference.child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
            if ( snapshot.hasChild(attendCode)){
                if (snapshot.child(attendCode).hasChild(FirebaseAuth.getInstance().getUid())){
                    Toast.makeText(StudentControlActivity.this,"You are already attended",Toast.LENGTH_LONG).show();
                }
                else {
                    reference.child(courseId).child(attendCode).child(FirebaseAuth.getInstance().getUid())
                            .child("status").setValue("I attended");

                    Toast.makeText(StudentControlActivity.this,"added",Toast.LENGTH_LONG).show();
                    GetOldGrade();
                }
             } else {
            Toast.makeText(StudentControlActivity.this,"Invalid Attend Code",Toast.LENGTH_LONG).show();
        }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void GetOldGrade(){
        reference1.child(FirebaseAuth.getInstance().getUid()).child(courseId).child("attandGrade").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int grade=snapshot.getValue(Integer.class);
                AddOldGradeToGrade(grade);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void AddOldGradeToGrade(int grade){
        int attendGrade=grade+1;
        reference1.child(FirebaseAuth.getInstance().getUid()).child(courseId).child("attandGrade").setValue(attendGrade);
        reference2.child(courseId).child("Student").child(FirebaseAuth.getInstance().getUid()).child("attandGrade").setValue(attendGrade);

    }
}