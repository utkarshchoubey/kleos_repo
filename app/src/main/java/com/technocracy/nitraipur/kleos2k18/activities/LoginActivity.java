package com.technocracy.nitraipur.kleos2k18.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.technocracy.nitraipur.kleos2k18.R;

import io.github.mthli.slice.Slice;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppCompatActivity {
 Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        customType(this, "fadein-to-fadeout");

        signup = (Button)findViewById(R.id.signup);
        Slice slice = new Slice(signup);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));
    }
}
