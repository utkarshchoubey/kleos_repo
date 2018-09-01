package com.technocracy.nitraipur.kleos2k18.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {
    private Activity activity;

    public UserPreferences(Activity activity){
        this.activity = activity;
    }

    public boolean checkLoggedIn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferences.getBoolean("LoggedIn", false);
    }
    public void setLoggedIn(boolean loggedIn) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("LoggedIn", loggedIn);
        editor.apply();
    }

}
