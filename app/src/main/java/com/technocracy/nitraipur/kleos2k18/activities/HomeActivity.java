package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.fragments.HintsFragment;
import com.technocracy.nitraipur.kleos2k18.fragments.LeaderboardFragment;
import com.technocracy.nitraipur.kleos2k18.fragments.ProfileFragment;
import com.technocracy.nitraipur.kleos2k18.fragments.QuestionFragment;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class HomeActivity extends AppCompatActivity {
    private SlidingRootNav slidingRootNav;
    AnimatedGradientTextView homeTextview;
    BottomNavigation bottomNavigation;
    RelativeLayout mainRelativeLayout;
    FrameLayout mainView;
    int prevFragmentPos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrameLayout, new QuestionFragment());
        ft.commit();

        homeTextview = (AnimatedGradientTextView) findViewById(R.id.home_textview);
        bottomNavigation = (BottomNavigation)findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int itemID, int position, boolean fromUser) {
                Fragment prevFragmnt = getSupportFragmentManager().findFragmentById(R.id.mainFrameLayout);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (itemID){
                    case R.id.questionTab:
                        homeTextview.setText("Questions");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                          .remove(prevFragmnt)
                          .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                          .replace(R.id.mainFrameLayout,new QuestionFragment())
                          .commit();
                        break;
                    case R.id.leaderboardtab:
                        homeTextview.setText("Leaderboard");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new LeaderboardFragment())
                                .commit();
                        break;
                    case R.id.hintTab:
                        homeTextview.setText("Hints");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new HintsFragment())
                                .commit();
                        break;
                    case R.id.profileTab:
                        homeTextview.setText("Profile");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new ProfileFragment())
                                .commit();
                        break;
                }
            }

            @Override
            public void onMenuItemReselect(int itemID, int position, boolean fromUser) {
                Fragment prevFragmnt = getSupportFragmentManager().findFragmentById(R.id.mainFrameLayout);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (itemID){
                    case R.id.questionTab:
                        homeTextview.setText("Questions");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new QuestionFragment())
                                .commit();
                        break;
                    case R.id.leaderboardtab:
                        homeTextview.setText("Leaderboard");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new LeaderboardFragment())
                                .commit();
                        break;
                    case R.id.hintTab:
                        homeTextview.setText("Hints");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new HintsFragment())
                                .commit();
                        break;
                    case R.id.profileTab:
                        homeTextview.setText("Profile");
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .remove(prevFragmnt)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.mainFrameLayout,new ProfileFragment())
                                .commit();
                        break;
                }
            }
        });
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.navigation_drawer)
                .inject();
    }

    public Context getContext(){
        return this;
    }


}
