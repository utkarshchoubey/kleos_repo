package com.technocracy.nitraipur.kleos2k18.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.activities.QuestionActivity;
import com.technocracy.nitraipur.kleos2k18.utils.Question;

import java.util.ArrayList;

import io.github.mthli.slice.Slice;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder>{
    ArrayList<Question> questions = new ArrayList<Question>();
    Context ct;
    FragmentManager fm;

    public QuestionRecyclerAdapter(Context ct){
        this.ct = ct;
        this.fm = fm;
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
        Slice slice = new Slice(holder.getLayout());
        slice.setColor(R.color.button);
        slice.setRipple(1);
        slice.setRadius(8.0f);

        holder.questionTextView.setText("Some Tittle");
       holder.questionContent.setText(R.string.loreIsum);
       holder.questionCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(ct, QuestionActivity.class);
               ct.startActivity(i);
           }
       });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
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
