package com.example.e_learning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.AdapterDisplayQuiz;
import com.example.e_learning.databinding.ActivityStudentQuizBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentQuizActivity extends AppCompatActivity {

    ActivityStudentQuizBinding binding;
   public static String courseId;
    DatabaseReference reference;

    ArrayList<String> quiz_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courseId = getIntent().getStringExtra("CourseId");
        reference = FirebaseDatabase.getInstance().getReference("Quizzes");
        AdapterDisplayQuiz adapterDisplayQuiz=new AdapterDisplayQuiz(quiz_list,this,courseId);
        reference.child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quiz_list.clear();
                if (snapshot.getValue()!=null){
                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        quiz_list.add(snapshot1.getKey());

                    }
                    adapterDisplayQuiz.setList(quiz_list);
                    binding.recyclerQuiz.setAdapter(adapterDisplayQuiz);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}