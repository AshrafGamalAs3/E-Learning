package com.example.e_learning;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e_learning.AdapterChat;
import com.example.e_learning.MessageData;
import com.example.e_learning.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    public static String courseId;
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
    public static String name;
    public static String courseName;
    public static String doctorId;
    ArrayList<MessageData> massageData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        String senderId=auth.getUid();
        courseId=getIntent().getStringExtra("CourseId");
        courseName=getIntent().getStringExtra("courseName");
        doctorId=getIntent().getStringExtra("doctorID");
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


        AdapterChat adapterChat=new AdapterChat(massageData,this,doctorId);
        binding.recyclerChat.setAdapter(adapterChat);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerChat.setLayoutManager(layoutManager);

        reference1.child("Chats").child(courseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                massageData.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    MessageData maData=snapshot1.getValue(MessageData.class);
                    massageData.add(maData);
                }
                adapterChat.setList(massageData);
                adapterChat.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String massage=binding.massage.getText().toString();
                if (!massage.isEmpty()){
                    MessageData messageData=new MessageData(senderId,massage,name);
                    binding.massage.setText("");
                    reference1.child("Chats").child(courseId).push().setValue(messageData);
                }else {
                    Toast.makeText(ChatActivity.this,"Please Enter Your Massage",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}