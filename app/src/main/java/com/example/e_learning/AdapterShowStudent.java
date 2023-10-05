package com.example.e_learning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learning.Students;

import java.util.ArrayList;

public class AdapterShowStudent extends RecyclerView.Adapter<AdapterShowStudent.ShowStudent> {

ArrayList<Students> student_list=new ArrayList<>();
Context context;

    public AdapterShowStudent(ArrayList<Students> student_list, Context context) {
        this.student_list = student_list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view=LayoutInflater.from(context).inflate(R.layout.item_grade,parent,false);
     return new ShowStudent(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowStudent holder, int position) {
        Students students=student_list.get(position);
        holder.textName1.setText("Name :");
        holder.textName.setText(students.getStudentName());
        String quiz=String.valueOf(students.getGradeQuze());
        String attend=String.valueOf(students.getAttandGrade());
        holder.textQuiz.setText(quiz);
        holder.textQuiz1.setText("QuizGrade :");
        holder.textAttend.setText(attend);
        holder.textAttend1.setText("AttendGrade :");
    }

    @Override
    public int getItemCount() {
      return   student_list==null?0:student_list.size();
    }

    public class ShowStudent extends RecyclerView.ViewHolder{

        TextView textName;
        TextView textName1;
        TextView textQuiz;
        TextView textQuiz1;
        TextView textProject;
        TextView textProject1;
        TextView textAttend;
        TextView textAttend1;
    public ShowStudent(@NonNull View itemView) {
        super(itemView);
        textName1=itemView.findViewById(R.id.student_name);
        textName=itemView.findViewById(R.id.student_name_result);
        textAttend=itemView.findViewById(R.id.Attand_grade_result);
        textAttend1=itemView.findViewById(R.id.Attand_grade);

        textQuiz=itemView.findViewById(R.id.quiz_grade_result);
        textQuiz1=itemView.findViewById(R.id.quiz_grade);

    }
}
}
