package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.e_learning.Const;

import com.example.e_learning.databinding.ActivityStudentOrinstractorBinding;

public class StudentORInstractor extends AppCompatActivity {
 ActivityStudentOrinstractorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStudentOrinstractorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.studentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    binding.instractorCheckBox.setChecked(false);
                    Const.userType=Const.STUDENT_USER;
                }else {
                    Const.userType="";
                }
            }
        });

        binding.instractorCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    binding.studentCheckBox.setChecked(false);
                    Const.userType=Const.INSTRUCTOR_USER;
                }else {
                    Const.userType="";
                }
            }
        });
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Const.userType==""){
                    Toast.makeText(StudentORInstractor.this,"Please select the type",Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(new Intent(StudentORInstractor.this,CreateAccount.class));
                }
            }
        });

    }

}