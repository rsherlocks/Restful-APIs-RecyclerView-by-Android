package com.example.androishaper.volleyrecyclerviewapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView HeadText,DescText;
    ImageView imageView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageId);
        HeadText=itemView.findViewById(R.id.textHeadId);
        DescText=itemView.findViewById(R.id.textDesId);

    }
}
