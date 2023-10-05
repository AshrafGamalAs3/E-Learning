package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_hero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_hero);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(splash_hero.this, StudentORInstractor.class));
            }
        },3000);
    }
}