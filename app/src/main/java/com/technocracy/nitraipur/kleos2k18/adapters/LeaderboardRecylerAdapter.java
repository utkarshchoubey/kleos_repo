package com.technocracy.nitraipur.kleos2k18.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.model.User;

import java.util.List;

import io.github.mthli.slice.Slice;

public class LeaderboardRecylerAdapter extends RecyclerView.Adapter<LeaderboardRecylerAdapter.LeaderBoardViewHolder> {

    Context ct;
    List<User> users;

    public LeaderboardRecylerAdapter(Context ct, List<User> users){
        this.ct = ct;
        this.users = users;
    }

    @NonNull
    @Override
    public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ct);
        View MyOwnView = myInflator.inflate(R.layout.leaderboard_item,parent,false);
        return new LeaderBoardViewHolder(MyOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardViewHolder holder, int position) {
        Slice slice = new Slice(holder.itemView.findViewById(R.id.leaderboadRV));
        slice.setRipple(1);
        slice.setRadius(8.0f);
        slice.setElevation(4.0f);

        holder.name.setText(String.valueOf(users.get(position).firstName).concat(" ".concat(String.valueOf(users.get(position).lastName))));
        holder.level.setText("Level ".concat(String.valueOf(users.get(position).level)));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderBoardViewHolder extends RecyclerView.ViewHolder {
        TextView name,level;
        public LeaderBoardViewHolder(View itemView) {
            super(itemView);
             name=(TextView)itemView.findViewById(R.id.nameTV);
             level=(TextView)itemView.findViewById(R.id.levelV);

        }
    }
}
