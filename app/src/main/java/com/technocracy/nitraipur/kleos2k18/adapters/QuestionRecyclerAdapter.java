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
import android.widget.Toast;

import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.activities.OtpActivity;
import com.technocracy.nitraipur.kleos2k18.activities.QuestionActivity;
import com.technocracy.nitraipur.kleos2k18.model.Question;
import com.technocracy.nitraipur.kleos2k18.model.User;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import am.appwise.components.ni.NoInternetDialog;
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionViewHolder>{
    //ArrayList<Question> questions = new ArrayList<Question>();
    Context ct;
    String level;


    public QuestionRecyclerAdapter(Context ct, String level){
        this.ct = ct;
        this.level = level;
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
        Slice slice = new Slice(holder.questionCard);
        slice.setRipple(1);
        slice.setRadius(8.0f);
        holder.avi.show();
        holder.questionTextView.setVisibility(View.INVISIBLE);
        holder.questionContent.setVisibility(View.INVISIBLE);

        ApiEndpoints apiBase = ApiBase.getClient().create(ApiEndpoints.class);
        Call<Question> call = apiBase.getQuestionbyId(String.valueOf(position + 1));
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(@NonNull Call<Question> call, @NonNull Response<Question> response) {
                if(response.isSuccessful()){
                    holder.avi.hide();
                    holder.questionTextView.setVisibility(View.VISIBLE);
                    holder.questionContent.setVisibility(View.VISIBLE);
                    if(!response.body().question.toString().equals("")){
                        holder.questionTextView.setText(response.body().title);
                        holder.questionContent.setText(response.body().question);
                        holder.questionCard.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ct, QuestionActivity.class);
                                i.putExtra("question",response.body());
                                i.putExtra("id",position+1);
                                ct.startActivity(i);
                            }
                        });
                    }else {
                        Toasty.error(ct, "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                    }
                }
                else{
                    Toasty.error(ct, "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(ct).build();

            }
        });



    }

    @Override
    public int getItemCount() {
        if(Integer.parseInt(level) == 12)
        return 12;
        else return Integer.parseInt(level)+1;

    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView questionTextView, questionContent;
        ConstraintLayout questionCard;
        ImageView questionImage;
        AVLoadingIndicatorView avi;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionCard = (ConstraintLayout)itemView.findViewById(R.id.questionCard);
            avi = (AVLoadingIndicatorView)itemView.findViewById(R.id.avi);
            questionTextView = (TextView)itemView.findViewById(R.id.questionTitle);
            questionContent = (TextView)itemView.findViewById(R.id.questionContent);
            questionImage = (ImageView)itemView.findViewById(R.id.questionImage);
        }




    }

}
