package com.example.e_learning;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.example.e_learning.databinding.ActivityStudentSolveQuizBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentSolveQuiz extends AppCompatActivity {

    ActivityStudentSolveQuizBinding binding;
  public static   String courseId;
  public static   String quizKey;
    ArrayList<Quiz> quiz_list=new ArrayList<>();
    DatabaseReference reference;
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
   public static String name;
    FirebaseDatabase database;
  public static short  rightAnswer=0;
  public  static short   grade=0;
  public static short  position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentSolveQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseId = getIntent().getStringExtra("CourseId");
        quizKey = getIntent().getStringExtra("quizKey");
        reference = FirebaseDatabase.getInstance().getReference("Quizzes");
        database = FirebaseDatabase.getInstance();

        reference.child(courseId).child(quizKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quiz_list.clear();
                if (snapshot.getValue() != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        quiz_list.add(dataSnapshot.getValue(Quiz.class));
                      }
                    SetQuestion();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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



        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswer != 0) {
                    CheckAnswer();
                    SetQuestion();
                }

                 else {
                    Toast.makeText(StudentSolveQuiz.this, "Choose Answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.firstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightAnswer = 1;
                binding.firstAnswer.setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.primaryTextColor));
                binding.firstAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.white));
                binding.secondAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.secondAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.thirdAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.thirdAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.fourAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.fourAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));

            }
        });
        binding.secondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightAnswer = 2;
                binding.secondAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.primaryTextColor));
                binding.secondAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.white));
                binding.firstAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.firstAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.thirdAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.thirdAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.fourAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.fourAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
            }
        });
        binding.thirdAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightAnswer = 3;
                binding.thirdAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.thirdAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.white));
                binding.firstAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.firstAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.secondAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.secondAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.fourAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.fourAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
            }
        });
        binding.fourAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightAnswer = 4;
                binding.fourAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.fourAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.white));
                binding.firstAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.firstAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.thirdAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.thirdAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
                binding.secondAnswer
                        .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
                binding.secondAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
            }
        });

    }

    private void SetQuestion(){
        if (position == (quiz_list.size() - 1)){
            binding.next.setText("Finish");
        }

            binding.textQuestion.setText(quiz_list.get(position).getQuestion());
            binding.firstAnswer.setText(quiz_list.get(position).getFirstAnswer());
            binding.secondAnswer.setText(quiz_list.get(position).getSecondAnswer());
            binding.thirdAnswer.setText(quiz_list.get(position).getThirdAnswer());
            binding.fourAnswer.setText(quiz_list.get(position).getFourAnswer());

    }

    private void CheckAnswer() {
    if (quiz_list.get(position).getAnswer()==rightAnswer){
        grade++;

    }
        if (position == (quiz_list.size() - 1)) {

            StudentSolveQuiz.this.finishAndRemoveTask();


        }
        else{
            position++;
        }

    resetAnswer();
    }
    private void UploadedResult(String courseId,String quizKey,int grade){
        String studentId=FirebaseAuth.getInstance().getUid();
Students students=new Students(studentId,courseId,quizKey,grade);
database.getReference().child("Quiz Answer").child(courseId).child(quizKey).child(FirebaseAuth.getInstance().getUid()).setValue(students);
reference1.child("Quiz Answer").child(courseId).child(quizKey).child(studentId).child("attandGrade").removeValue();
reference1.child("Quiz Answer").child(courseId).child(quizKey).child(studentId).child("projectGrade").removeValue();
GetOldGrade(grade,studentId);
    }
    private void GetOldGrade(int grade,String studentId){

        reference1.child("Courses").child(courseId).child("Student").child(studentId).child("gradeQuze").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int gradeQuiz=snapshot.getValue(Integer.class);
                gradeQuiz+=grade;
                SetGradeToStudentTOStudents(gradeQuiz,studentId);
                SetGradeToStudentCourse(gradeQuiz,studentId);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void SetGradeToStudentCourse(int gradeQuiz,String studentId){
        reference1.child("Courses").child(courseId).child("Student").child(studentId).child("gradeQuze").setValue(gradeQuiz);


    }
    private void SetGradeToStudentTOStudents(int gradeQuiz,String studentId){
        reference1.child("Students").child(studentId).child(courseId).child("gradeQuze").setValue(gradeQuiz);
    }

    private void resetAnswer ()
    {
        rightAnswer = 0 ;
        binding.firstAnswer
                .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
        binding.firstAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
        binding.fourAnswer
                .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
        binding.fourAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
        binding.thirdAnswer
                .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
        binding.thirdAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
        binding.secondAnswer
                .setBackgroundColor(ContextCompat.getColor(StudentSolveQuiz.this, R.color.white));
        binding.secondAnswer.setTextColor(ContextCompat.getColor(StudentSolveQuiz.this,R.color.primaryTextColor));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(StudentSolveQuiz.this, "Your Answer has been uploaded", Toast.LENGTH_SHORT).show();
        UploadedResult(courseId,quizKey,grade);
        binding = null ;
    }
}
