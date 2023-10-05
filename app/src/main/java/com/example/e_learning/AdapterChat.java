package com.example.e_learning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdapterChat extends RecyclerView.Adapter {

ArrayList<MessageData>massage_list=new ArrayList<>();
Context context;
String receiverId;
int sender=1;
int receiver=2;

    public AdapterChat(ArrayList<MessageData> massage_list, Context context, String receiverId) {
        this.massage_list = massage_list;
        this.context = context;
        this.receiverId = receiverId;
    }
    public void setList(ArrayList<MessageData> massage_list) {
        this.massage_list = massage_list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==sender) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_item, parent, false);
            return new SenderHolderChat(view);
        }
        else{
            View view=LayoutInflater.from(context).inflate(R.layout.reciver_item,parent,false);
            return new ReciverHolderChat(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageData messageData=massage_list.get(position);
        if (holder.getClass()==SenderHolderChat.class){
            ( (SenderHolderChat) holder).senderMassage.setText(messageData.getMassage());
            ( (SenderHolderChat) holder).senderName.setText(messageData.getUserName());

        }
        else{
            (  (ReciverHolderChat) holder).receiverMassage.setText(messageData.getMassage());
            (  (ReciverHolderChat) holder).receiverName.setText(messageData.getUserName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        MessageData maData=massage_list.get(position);
        if (maData.getUserId().equals(FirebaseAuth.getInstance().getUid())){

            return sender;
        }
        else{
            return receiver;
        }
    }

    @Override
    public int getItemCount() {
        return massage_list==null?0:massage_list.size();
    }

    public class ReciverHolderChat extends RecyclerView.ViewHolder{
        TextView receiverMassage,receiverName;
        public ReciverHolderChat(@NonNull View itemView) {
            super(itemView);
            receiverMassage=itemView.findViewById(R.id.receiver_text);
            receiverName=itemView.findViewById(R.id.receiver_name);

        }
    }
    public class SenderHolderChat extends RecyclerView.ViewHolder{
        TextView senderMassage,senderName;
        public SenderHolderChat(@NonNull View itemView) {
            super(itemView);
            senderMassage=itemView.findViewById(R.id.sender_text);
            senderName=itemView.findViewById(R.id.senderName);
        }
    }
}
