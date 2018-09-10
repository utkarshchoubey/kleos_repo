package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import java.util.Random;

import am.appwise.components.ni.ConnectionCallback;
import am.appwise.components.ni.NoInternetDialog;

public class SplashScreen extends AwesomeSplash  {
    UserPreferences userPreferences;
    NoInternetDialog noInternetDialog ;
    @Override
    public void initSplash(ConfigSplash configSplash) {
        userPreferences = new UserPreferences(this);

        configSplash.setBackgroundColor(R.color.colorPrimary);

        configSplash.setAnimCircularRevealDuration(2000);
        Random r = new Random();
        int Low = 10;
        int High = 100;
        int result = r.nextInt(High-Low) + Low;
        if(result %2 ==0 )configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        else configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);
        configSplash.setLogoSplash(R.drawable.splash); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);
        configSplash.setTitleSplash("Kleos 2K18");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(40f);
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);
        configSplash.setTitleFont("fonts/caviardreams.ttf");


    }


    @Override
    public void animationsFinished() {
            String a = userPreferences.getName();
            if(!userPreferences.getName().equals("")){
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
            }else{
                userPreferences.clearPrefs();
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
