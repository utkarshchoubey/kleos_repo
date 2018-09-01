package com.technocracy.nitraipur.kleos2k18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.technocracy.nitraipur.kleos2k18.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        customType(this, "fadein-to-fadeout");
    }

    public void backToHomeActivity(View view) {
        finish();
    }
}
