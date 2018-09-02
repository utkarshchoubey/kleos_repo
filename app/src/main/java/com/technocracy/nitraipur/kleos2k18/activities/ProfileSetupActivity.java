package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.technocracy.nitraipur.kleos2k18.R;

import io.github.mthli.slice.Slice;

import static maes.tech.intentanim.CustomIntent.customType;

public class ProfileSetupActivity extends AppCompatActivity {
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
        customType(this, "fadein-to-fadeout");

        submit = (Button)findViewById(R.id.submit);
        Slice slice = new Slice(submit);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));
    }

    public void submit(View v){

        Intent i = new Intent(ProfileSetupActivity.this, HomeActivity.class);
        startActivity(i);
        finish();

    }
}
