package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthProvider;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.fragments.HintsFragment;
import com.technocracy.nitraipur.kleos2k18.fragments.LeaderboardFragment;
import com.technocracy.nitraipur.kleos2k18.fragments.ProfileFragment;
import com.technocracy.nitraipur.kleos2k18.fragments.QuestionsFragment;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends AppCompatActivity {
    private SlidingRootNav slidingRootNav;
    AnimatedGradientTextView homeTextview;
    RelativeLayout mainRelativeLayout;
    FrameLayout mainView;
    BottomNavigation bottomNavigation;
    int prevFragmentPos = 0;
    ConstraintLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        customType(this, "fadein-to-fadeout");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainLayout = (ConstraintLayout)findViewById(R.id.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Depth depth = DepthProvider.getDepth(this);
        depth.setFragmentContainer(R.id.mainFrameLayout);
        depth.animate().enter(new QuestionsFragment()).start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }, 1700);

            }
        });

        homeTextview = (AnimatedGradientTextView) findViewById(R.id.kleos_textview);

        bottomNavigation= (BottomNavigation)findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int itemID, int position, boolean fromUser) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Fragment oldFragment = getSupportFragmentManager().findFragmentById(R.id.mainFrameLayout);
                if (oldFragment==null){
                    switch (prevFragmentPos){
                        case 0: oldFragment = new QuestionsFragment(); break;
                        case 1: oldFragment = new LeaderboardFragment(); break;
                        case 2: oldFragment = new HintsFragment(); break;
                        case 3: oldFragment = new ProfileFragment(); break;
                        default: oldFragment = new QuestionsFragment();
                    }
                }
                Depth depth = DepthProvider.getDepth(HomeActivity.this);
                depth.setFragmentContainer(R.id.mainFrameLayout);
                Fragment newFragment = new QuestionsFragment();
                switch (itemID){
                    case R.id.questionTab:
                        homeTextview.setText("Questions");
                        newFragment = new QuestionsFragment();
                        break;
                    case R.id.leaderboardtab:
                        homeTextview.setText("Leaderboard");
                        newFragment = new LeaderboardFragment();
                        break;
                    case R.id.hintTab:
                        homeTextview.setText("Hints");
                        newFragment = new HintsFragment();
                        break;
                    case R.id.profileTab:
                        homeTextview.setText("Profile");
                        newFragment = new ProfileFragment();
                        break;
                }
                depth.animate()
                        .reduce(oldFragment)
                        .exit(oldFragment)
                        .enter(newFragment)
                        .start();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                        }, 3400);

                    }
                });
                prevFragmentPos = position;
            }
            @Override
            public void onMenuItemReselect(int itemID, int position, boolean fromUser) {
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
