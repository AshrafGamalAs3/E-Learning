package com.example.e_learning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learning.Quiz;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdapterDisplayQuiz extends RecyclerView.Adapter<AdapterDisplayQuiz.DisplayQuiz> {
    List<String> quiz_list;
    private OnClick onClick ;
    Context context;
    String courseId;
    String quizId;
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();


    ArrayList<Quiz> quizArrayList=new ArrayList<>();
    public void setList(List<String> quiz_list) {
        this.quiz_list = quiz_list;
    }
    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


    public AdapterDisplayQuiz(List<String> quiz_list, Context context, String courseId) {
        this.quiz_list = quiz_list;
        this.context = context;
        this.courseId = courseId;

    }

    @NonNull
    @Override
    public DisplayQuiz onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz,parent,false);
        return new DisplayQuiz(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayQuiz holder, int position) {
        holder.quiz.setText("Quiz "+(position+1));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String studentId=FirebaseAuth.getInstance().getUid();
                    reference1.child("Quiz Answer").child(courseId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(quiz_list.get(holder.getAdapterPosition()))){
                                Toast.makeText(context, "You have already solved this quiz", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent =new Intent(context, StudentSolveQuiz.class);
                                intent.putExtra("CourseId",courseId);
                                intent.putExtra("quizKey",quiz_list.get(holder.getAdapterPosition()));
                                context.startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            });

    }

    @Override
    public int getItemCount() {
        return quiz_list==null?0:quiz_list.size();
    }

    public class DisplayQuiz extends RecyclerView.ViewHolder{
TextView quiz;
        public DisplayQuiz(@NonNull View itemView) {
            super(itemView);
            quiz=itemView.findViewById(R.id.Quiz);
            if (onClick != null){

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.onItemClick(quiz_list.get(getAdapterPosition()));
                    }
                });
            }
        }
    }
    public interface OnClick {

        void onItemClick(String name);
    }
}
