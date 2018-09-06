package com.technocracy.nitraipur.kleos2k18.spons;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.technocracy.nitraipur.kleos2k18.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<MyViewHolder> {
private ArrayList<Datalist> list;

    public Adapter(ArrayList<Datalist> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.spons_items,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageView imv;
        imv=(ImageView)holder.v.findViewById(R.id.imageview);
        imv.setImageResource(list.get(position).getImageview());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
