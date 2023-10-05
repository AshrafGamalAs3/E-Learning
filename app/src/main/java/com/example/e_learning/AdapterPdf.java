package com.example.e_learning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learning.Material;

import java.util.ArrayList;
import java.util.List;

public class AdapterPdf extends RecyclerView.Adapter<AdapterPdf.ShowPdf> {
List<String> material_list;
ArrayList<Material> material;
    Context context;
    public void setList(List<String> material_list) {
        this.material_list = material_list;
    }

    public AdapterPdf(List<String> material_list, Context context) {
        this.material_list = material_list;
        this.context = context;
    }

    public AdapterPdf(List<String> material_list, ArrayList<Material> material, Context context) {
        this.material_list = material_list;
        this.material = material;
        this.context = context;
    }

    public AdapterPdf() {
    }

    @NonNull
    @Override
    public ShowPdf onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pdf,parent,false);
       return new ShowPdf(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowPdf holder, int position) {

    holder.textView_Material.setText("Material "+(position+1));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(material_list.get()));
//          context.startActivity(browserIntent);
        }
    });


    }

    @Override
    public int getItemCount() {
        return material_list==null?0:material_list.size();
    }

    public class ShowPdf extends RecyclerView.ViewHolder{
        TextView textView_Material;

        public ShowPdf(@NonNull View itemView) {
            super(itemView);
            textView_Material=itemView.findViewById(R.id.Material);


        }
    }
}
