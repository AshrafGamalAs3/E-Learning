package com.example.e_learning;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.databinding.ActivityControlCourseBinding;

public class ControlCourse extends AppCompatActivity {
ActivityControlCourseBinding binding;

    String courseId;
 String doctorId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityControlCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


      courseId=getIntent().getStringExtra("courseId");
      doctorId=getIntent().getStringExtra("doctorId");

   String  courseName=getIntent().getStringExtra("courseName");
      binding.CourseId.setText(courseId);
      binding.text.setText(courseName);
      binding.CourseId.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ClipboardManager cm=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
              cm.setText( binding.CourseId.getText());
              Toast.makeText(ControlCourse.this,"Copied",Toast.LENGTH_LONG).show();
          }
      });

        binding.arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ControlCourse.this, Doctor.class));

            }
        });

        binding.OpenAttand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ControlCourse.this, AttandActivity.class);
                intent.putExtra("CourseId",binding.CourseId.getText());
                startActivity(intent);
            }
        });
        binding.OpenQuze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ControlCourse.this, QizeActivity.class);
                intent.putExtra("CourseId",binding.CourseId.getText());
                startActivity(intent);
            }
        });
        binding.OpenMatrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ControlCourse.this, MatrialActivity.class);
                intent.putExtra("CourseId",binding.CourseId.getText());
                startActivity(intent);
            }
        });
        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ControlCourse.this, ChatActivity.class);
                intent.putExtra("CourseId",binding.CourseId.getText());
                intent.putExtra("doctorID",binding.CourseId.getText());
                startActivity(intent);
            }
        });
        binding.OpenGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ControlCourse.this, GradeActivity.class);
                intent.putExtra("CourseId",binding.CourseId.getText());

                startActivity(intent);
            }
        });
    }

}