package com.technocracy.nitraipur.kleos2k18.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.viewtooltip.ViewTooltip;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.model.Question;
import com.technocracy.nitraipur.kleos2k18.model.User;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;

import am.appwise.components.ni.NoInternetDialog;
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tyrantgit.explosionfield.ExplosionField;

import static maes.tech.intentanim.CustomIntent.customType;

public class QuestionActivity extends AppCompatActivity {
    TextView tv,tv1;
    UserPreferences userPreferences;
    EditText ed;
    TextInputLayout til;
    ApiEndpoints apiBase;
    Button button;
    Question q;
    ExplosionField mExplosionField;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        customType(this, "fadein-to-fadeout");
        userPreferences = new UserPreferences(this);

        mExplosionField = ExplosionField.attach2Window(this);

        tv=(TextView)findViewById(R.id.questionText);
        tv1=(TextView)findViewById(R.id.kleos);
        til = (TextInputLayout)findViewById(R.id.til);
        ed=(EditText)findViewById(R.id.answer);

        button=(Button)findViewById(R.id.submitB);
        Slice slice = new Slice(button);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));



        q = getIntent().getParcelableExtra("question");
        pos = getIntent().getExtras().getInt("id");
        tv1.setText(q.title);
        tv.setText(q.question);

        int level = Integer.parseInt(userPreferences.getLevel());

        if(pos <= level){
            button.setVisibility(View.GONE);
            ed.setVisibility(View.GONE);
            til.setVisibility(View.GONE);
            til.setEnabled(false);
            button.setEnabled(false);
            ed.setEnabled(false);
        }

        apiBase = ApiBase.getClient().create(ApiEndpoints.class);
    }

    public void backToHomeActivity(View view) {
        finish();
    }

    public void submitAnswer(View view) {
        if(ed.getText().toString().length() > 0){
            mExplosionField.explode(view);
            view.setVisibility(View.INVISIBLE);
            view.setEnabled(false);
            Call<User> call = apiBase.submitAnswer(userPreferences.getUsername(),String.valueOf(pos),String.valueOf(ed.getText()));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if(response.isSuccessful()){
                        if(String.valueOf(response.body().message).equals("Congratulations your answer is correct")){
                            userPreferences.setLevel(String.valueOf(pos));
                            Toasty.success(QuestionActivity.this, "Congratulations your answer is correct!!", Toast.LENGTH_SHORT, true).show();
                        }
                        else{
                            Toasty.error(QuestionActivity.this, "Sorry Wrong Answer.Please Try Again!!", Toast.LENGTH_SHORT, true).show();
                            view.setVisibility(View.VISIBLE);
                            view.setEnabled(true);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    view.setVisibility(View.VISIBLE);
                    view.setEnabled(true);
                    NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(QuestionActivity.this).build();
                }
            });


        }else{
            ViewTooltip.on(view)
                    .align(ViewTooltip.ALIGN.CENTER)
                    .position(ViewTooltip.Position.TOP)
                    .text("This Field Can't be Empty.")
                    .textColor(Color.WHITE)
                    .color(Color.parseColor("#00BB84"))
                    .padding(2,2,2,2)
                    .corner(15)
                    .arrowWidth(15)
                    .arrowHeight(15)
                    .distanceWithView(0)
                    .show();
            return;
        }
    }
}
