package com.example.e_learning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class AdapterStudent extends RecyclerView.Adapter<AdapterStudent.addCourse> {
    ArrayList<Courses> Course_List = new ArrayList<>();
    Context context;

    public AdapterStudent(ArrayList<Courses> course_List, Context context) {
        Course_List = course_List;
        this.context = context;
    }

    @NonNull
    @Override
    public addCourse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_course,parent,false);
        return new addCourse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addCourse holder, int position) {
        Courses courses=Course_List.get(position);
        holder.textView.setText(courses.getCourseName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, StudentControlActivity.class);
                intent.putExtra("courseId",courses.getCourseId());
                intent.putExtra("courseName",courses.getCourseName());
                intent.putExtra("doctorId",courses.getInstructorId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Course_List==null?0:Course_List.size();
    }


    public class addCourse extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;


        public addCourse(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_Course);
           textView=itemView.findViewById(R.id.Course_Name1);
        }
    }
}





