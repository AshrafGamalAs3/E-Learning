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
import com.example.e_learning.databinding.ActivityGradeBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class AdapterCourse extends RecyclerView.Adapter<AdapterCourse.NewCourse> {


    ArrayList<Courses> Course_List=new ArrayList<>();

    Context context;

    public AdapterCourse(ArrayList<Courses> course_List, Context context) {
       this.Course_List = course_List;
        this.context = context;
    }

    @NonNull
    @Override
    public NewCourse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.item_course,parent,false);

        return new NewCourse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCourse holder, int position) {
    Courses courses=Course_List.get(position);
    holder.textView.setText(courses.courseName);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ControlCourse.class);
            intent.putExtra("courseId",courses.getCourseId());
            intent.putExtra("courseName",courses.getCourseName());
            context.startActivity(intent);
        }
    });
    }
    @Override
    public int getItemCount() {
        return Course_List==null?0:Course_List.size();
    }

    public class NewCourse extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public NewCourse(@NonNull View itemView) {
            super(itemView);
          imageView=itemView.findViewById(R.id.image_Course);
          textView=itemView.findViewById(R.id.Course_Name1);
         
        }
    }

}
