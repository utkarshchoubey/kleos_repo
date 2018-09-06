package com.technocracy.nitraipur.kleos2k18.leaderboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.technocracy.nitraipur.kleos2k18.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<DataList> dataList;

    // Provide a reference to the views for each data item
// Complex data leaderboard_items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Adapter(ArrayList<DataList> dataList) {
        this.dataList = dataList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_items, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView title = (TextView) holder.view.findViewById(R.id.tv);
       CircleImageView imageView=(CircleImageView)holder.view.findViewById(R.id.imv);

ImageView imageView2=(ImageView)holder.view.findViewById(R.id.imv2);


        title.setText(dataList.get(position).getTitle());
        imageView.setImageResource(dataList.get(position).getImage());

imageView2.setImageResource(dataList.get(position).getImage2());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
