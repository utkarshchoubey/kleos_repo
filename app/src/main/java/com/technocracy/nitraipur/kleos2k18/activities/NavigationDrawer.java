package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationDrawer extends AppCompatActivity {
    UserPreferences preferences;
    TextView name,level;
    CircleImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        StatusBarUtil.setTransparent(NavigationDrawer.this);

        preferences = new UserPreferences(this);
        imageView = (CircleImageView)findViewById(R.id.drawerImg);
        imageView.setImageURI(preferences.getProfileImage());

        name.setText(preferences.getName());
        level.setText("Level "+ preferences.getLevel());


    }

    public void logout(View view) {
        preferences.clearPrefs();
        Intent i = new Intent(this, SplashScreen.class);
        startActivity(i);
    }

    public void team(View view) {
        Intent i = new Intent(this, TeamActivity.class);
        startActivity(i);
    }

    public void storyline(View view) {
        Intent i = new Intent(this, StoryLineActivity.class);
        startActivity(i);
    }

    public void sponsor(View view) {
        Intent i = new Intent(this, SponsorsActivity.class);
        startActivity(i);
    }
}
