package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


import com.technocracy.nitraipur.kleos2k18.R;
import com.viven.imagezoom.ImageZoomHelper;

import static maes.tech.intentanim.CustomIntent.customType;

public class StoryLineActivity extends AppCompatActivity {

    ImageZoomHelper imageZoomHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_line);
        customType(this, "fadein-to-fadeout");

        imageZoomHelper = new ImageZoomHelper(this);

        ImageZoomHelper.setViewZoomable(findViewById(R.id.storyline));

    }
    public void exit(View view){
        finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }
}
