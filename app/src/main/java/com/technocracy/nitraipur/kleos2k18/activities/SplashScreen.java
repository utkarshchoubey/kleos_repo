package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.Window;

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
        configSplash.setTitleFont("fonts/caviardreams_bold.ttf");


    }


    @Override
    public void animationsFinished() {
            if(userPreferences.checkLoggedIn()){
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
            }else{
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
