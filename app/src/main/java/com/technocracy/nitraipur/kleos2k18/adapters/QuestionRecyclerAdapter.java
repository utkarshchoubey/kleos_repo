package com.technocracy.nitraipur.kleos2k18.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPagerAdapter;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.utils.Question;

import java.util.ArrayList;

import io.github.mthli.slice.Slice;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder>{
    ArrayList<Question> questions = new ArrayList<Question>();
    Context ct;
    private static final int VIEW_TYPE_TOP = 0x01;
    private static final int VIEW_TYPE_CENTER = 0x02;
    private static final int VIEW_TYPE_BOTTOM = 0x03;

    public QuestionRecyclerAdapter(Context ct){
        this.ct = ct;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_TOP;
        } else if (position == getItemCount() - 1) {
            return VIEW_TYPE_BOTTOM;
        } else {
            return VIEW_TYPE_CENTER;
        }
    }
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ct);
        View MyOwnView = myInflator.inflate(R.layout.question_card,parent,false);
        return new QuestionViewHolder(MyOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Slice slice = new Slice(holder.getLayout());
        slice.setElevation(2.0f);
            slice.setRadius(8.0f);


        holder.questionTextView.setText("Some Tittle");
       holder.questionContent.setText(R.string.loreIsum);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView, questionContent;
        ConstraintLayout questionCard;
        ImageView questionImage;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionCard = (ConstraintLayout)itemView.findViewById(R.id.questionCard);
            questionTextView = (TextView)itemView.findViewById(R.id.questionTitle);
            questionContent = (TextView)itemView.findViewById(R.id.questionContent);
            questionImage = (ImageView)itemView.findViewById(R.id.questionImage);
        }
        public ConstraintLayout getLayout(){
            return questionCard;
        }
    }

}
