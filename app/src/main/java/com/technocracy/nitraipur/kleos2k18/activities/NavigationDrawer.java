package com.technocracy.nitraipur.kleos2k18.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.technocracy.nitraipur.kleos2k18.R;

public class NavigationDrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        StatusBarUtil.setTransparent(NavigationDrawer.this);

    }
}
