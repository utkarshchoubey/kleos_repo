package com.technocracy.nitraipur.kleos2k18.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.viewtooltip.ViewTooltip;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.model.Message;
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

import static maes.tech.intentanim.CustomIntent.customType;

public class QuestionActivity extends AppCompatActivity {
    TextView tv,tv1;
    UserPreferences userPreferences;
    EditText ed;
    ApiEndpoints apiBase;
    Button button;
    Question q;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        customType(this, "fadein-to-fadeout");

        tv=(TextView)findViewById(R.id.questionText);
        tv1=(TextView)findViewById(R.id.tv1);
        ed=(EditText)findViewById(R.id.answer);

        button=(Button)findViewById(R.id.submitB);
        Slice slice = new Slice(button);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));

        q = getIntent().getParcelableExtra("question");
        pos = getIntent().getExtras().getInt("id");
        tv1.setText(q.title);
        tv.setText(q.question);
        userPreferences = new UserPreferences(this);
        apiBase = ApiBase.getClient().create(ApiEndpoints.class);
    }

    public void backToHomeActivity(View view) {
        finish();
    }

    public void submitAnswer(View view) {
        if(ed.getText().toString().length() > 0){
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
                            Toasty.error(QuestionActivity.this, "Sorry Wrong Answer.", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
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
