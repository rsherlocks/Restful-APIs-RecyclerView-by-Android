package com.example.androishaper.volleyrecyclerviewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<MyItem> Mylist;
    private Context context;

    public MyAdapter(List<MyItem> mylist, Context context) {
        this.Mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyItem myItemPosition=Mylist.get(position);
        holder.HeadText.setText(myItemPosition.getHead());
        holder.DescText.setText(myItemPosition.getDesc());
        Picasso.get().load(myItemPosition.getImageUrl()).into(holder.imageView);
        holder.HeadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,myItemPosition.getHead(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Mylist.size();
    }
}
