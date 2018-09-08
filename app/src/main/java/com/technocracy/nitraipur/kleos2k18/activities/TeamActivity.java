package com.technocracy.nitraipur.kleos2k18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technocracy.nitraipur.kleos2k18.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class TeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        customType(this, "fadein-to-fadeout");
    }
}
