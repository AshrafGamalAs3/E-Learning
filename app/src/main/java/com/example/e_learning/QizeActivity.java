package com.example.e_learning;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_learning.Quiz;
import com.example.e_learning.databinding.ActivityQizeBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QizeActivity extends AppCompatActivity {
ActivityQizeBinding binding;
public static short answer=0;
FirebaseDatabase database;
    String courseId;
    ArrayList<Quiz>quiz_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQizeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        courseId=getIntent().getStringExtra("CourseId");
binding.firstCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            binding.secondCheckBox.setChecked(false);
            binding.thirdCheckBox.setChecked(false);
            binding.fourCheckBox.setChecked(false);
            answer=1;

        }



    }
});
binding.secondCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
      if (b){
          binding.firstCheckBox.setChecked(false);
          binding.thirdCheckBox.setChecked(false);
          binding.fourCheckBox.setChecked(false);
          answer=2;
      }

    }
});
binding.thirdCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    if (b){
        binding.firstCheckBox.setChecked(false);
        binding.secondCheckBox.setChecked(false);
        binding.fourCheckBox.setChecked(false);
        answer=3;
    }

    }
});
binding.fourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
     if (b){
         binding.firstCheckBox.setChecked(false);
         binding.thirdCheckBox.setChecked(false);
         binding.secondCheckBox.setChecked(false);
         answer=4;
     }

    }
});
binding.nextQuestion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String question=binding.questionQuiz.getText().toString();
        String firstAnswer=binding.firstAnswer.getText().toString();
        String secondAnswer=binding.secondAnswer.getText().toString();
        String thirdAnswer=binding.thirdAnswer.getText().toString();
        String fourAnswer=binding.fourAnswer.getText().toString();
        CheckedQuestion( question,firstAnswer,secondAnswer,thirdAnswer, fourAnswer);
        AddQuestionToList( question,firstAnswer,secondAnswer,thirdAnswer, fourAnswer, answer);

    }
});



        binding.addQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendQuizToRealTime(quiz_list);
            }
        });

    }
    private void CheckedQuestion( String question, String firstAnswer,String secondAnswer,String thirdAnswer,String fourAnswer){
        if (question.length()>0&&firstAnswer.length()>0&&secondAnswer.length()>0&&thirdAnswer.length()>0&&fourAnswer.length()>0){
            binding.questionQuiz.setText("");;
            binding.firstAnswer.setText("");
            binding.secondAnswer.setText("");
            binding.thirdAnswer.setText("");
            binding.fourAnswer.setText("");
            binding.secondCheckBox.setChecked(false);
            binding.firstCheckBox.setChecked(false);
            binding.thirdCheckBox.setChecked(false);
            binding.fourCheckBox.setChecked(false);
        }  else if (question.isEmpty()&&firstAnswer.isEmpty()&&secondAnswer.isEmpty()&&thirdAnswer.isEmpty()&&fourAnswer.isEmpty()){
            Toast.makeText(QizeActivity.this, "Please enter the question and answer", Toast.LENGTH_SHORT).show();
        } else if (question.isEmpty()) {
            Toast.makeText(QizeActivity.this, "Please enter the question", Toast.LENGTH_SHORT).show();
        } else if (firstAnswer.isEmpty()) {
            Toast.makeText(QizeActivity.this, "Please enter First Answer ", Toast.LENGTH_SHORT).show();
        } else if (secondAnswer.isEmpty()) {
            Toast.makeText(QizeActivity.this, "Please enter Second Answer ", Toast.LENGTH_SHORT).show();
        } else if (thirdAnswer.isEmpty()) {
            Toast.makeText(QizeActivity.this, "Please enter Third Answer ", Toast.LENGTH_SHORT).show();
        } else if (fourAnswer.isEmpty()) {
            Toast.makeText(QizeActivity.this, "Please enter Four Answer ", Toast.LENGTH_SHORT).show();
        } else if ( binding.firstCheckBox==null&&binding.secondCheckBox==null&&binding.thirdCheckBox==null&&binding.fourCheckBox==null){
            Toast.makeText(QizeActivity.this, "Please Choose the answer ", Toast.LENGTH_SHORT).show();
        }
      }
    private void SendQuizToRealTime(ArrayList<Quiz>list){
        if (list==null){
            Toast.makeText(QizeActivity.this, "Please added Question ", Toast.LENGTH_SHORT).show();
        } else {
            database.getReference().child("Quizzes").child(courseId).push().setValue(list);
            list.clear();
        }


    }
    private void AddQuestionToList( String question, String firstAnswer,String secondAnswer,String thirdAnswer,String fourAnswer,int answer){
        Quiz quiz=new Quiz(question,firstAnswer,secondAnswer,thirdAnswer,fourAnswer,answer);
        quiz_list.add(quiz);
    }
}