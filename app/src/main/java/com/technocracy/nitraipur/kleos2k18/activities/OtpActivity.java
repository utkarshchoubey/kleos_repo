package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.florent37.viewtooltip.ViewTooltip;
import com.mursaat.extendedtextview.AnimatedGradientTextView;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.models.User;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;

import am.appwise.components.ni.NoInternetDialog;
import cn.iwgang.countdownview.CountdownView;
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static maes.tech.intentanim.CustomIntent.customType;

public class OtpActivity extends AppCompatActivity {

    Button resend, otpButton;
    TextInputEditText otpEdit;
    UserPreferences userPreferences;
    AVLoadingIndicatorView indicatorView;
    ApiEndpoints apiBase;
    CountdownView mCvCountdownView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        customType(this, "fadein-to-fadeout");

        userPreferences = new UserPreferences(this);

        AnimatedGradientTextView textView = (AnimatedGradientTextView)findViewById(R.id.kleos);
        textView.setTextSize(getResources().getDimension(R.dimen.textsize));

        resend = (Button)findViewById(R.id.resend);
        resend.setVisibility(View.INVISIBLE);
        otpButton = (Button)findViewById(R.id.otpButton);
        Slice slice = new Slice(resend);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));
        Slice slice1 = new Slice(otpButton);
        slice1.setRadius(8f);
        slice1.setColor(Color.parseColor("#00BB84"));

        indicatorView = (AVLoadingIndicatorView)findViewById(R.id.aviOtp);
        indicatorView.hide();

        otpEdit = (TextInputEditText)findViewById(R.id.otpEditText);

        apiBase = ApiBase.getClient().create(ApiEndpoints.class);

        resend.setVisibility(View.INVISIBLE);
        mCvCountdownView = (CountdownView)findViewById(R.id.countdown);
        mCvCountdownView.start(90000);

        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                YoYo.with(Techniques.FadeIn)
                        .duration(500)
                        .playOn(resend);
                resend.setVisibility(View.VISIBLE);
            }
        });
        otpEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length() == 6){
                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.length() == 6){
                   try {
                       InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                   } catch (Exception e) {

                   }
               }
            }

            @Override
            public void afterTextChanged(Editable s) {
               if(s.length() == 6){
                   try {
                       InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                   } catch (Exception e) {

                   }
               }
            }
        });


    }
    public void showViewTooltip(View v, String message){
        ViewTooltip.on(v)
                .align(ViewTooltip.ALIGN.CENTER)
                .position(ViewTooltip.Position.BOTTOM)
                .text(message)
                .textColor(Color.WHITE)
                .color(Color.parseColor("#00BB84"))
                .padding(2,2,2,2)
                .corner(15)
                .arrowWidth(15)
                .arrowHeight(15)
                .distanceWithView(0)
                .show();
    }

    public void otp(View view) {

        if(String.valueOf(otpEdit.getText()).length() == 6){
            YoYo.with(Techniques.FadeOut).duration(500).playOn(view);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            indicatorView.show();
                            view.setVisibility(View.INVISIBLE);
                        }
                    }, 500);

                }
            });
            mCvCountdownView.pause();
        String username = userPreferences.getUsername();
        String otp = otpEdit.getText().toString();
            Call<User> call = apiBase.otpVerification(username, otp);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if(response.isSuccessful()) {
                        if (String.valueOf(response.body().message).equals("Otp Verified")) {
                            mCvCountdownView.stop();
                            Intent i = new Intent(OtpActivity.this, ProfileSetupActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            indicatorView.hide();
                            YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                            view.setVisibility(View.VISIBLE);
                            Toasty.error(OtpActivity.this, "OTP didn't match", Toast.LENGTH_SHORT, true).show();
                            mCvCountdownView.restart();
                        }

                    }else {
                            indicatorView.hide();
                            YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                            view.setVisibility(View.VISIBLE);
                        try {
                            Toasty.error(OtpActivity.this, String.valueOf(response.errorBody().string()), Toast.LENGTH_SHORT, true).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                    view.setVisibility(View.VISIBLE);
                    indicatorView.hide();
                    NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(OtpActivity.this).build();
                }
            });
        }
        else{
            YoYo.with(Techniques.Shake).duration(500).playOn(view);
            showViewTooltip(otpEdit, "Invalid OTP");
        }
    }

    public void resend(View view) {

        String username = userPreferences.getUsername();
        String password = userPreferences.getPassword();
        YoYo.with(Techniques.FadeOut).duration(500).playOn(view);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        indicatorView.show();
                        view.setVisibility(View.INVISIBLE);
                    }
                }, 500);

            }
        });
        final User user = new User(username,password);
        Call<User> call = apiBase.createUser(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    user.setMessage(String.valueOf(response.body()));
                    if(user.getMessage().equals("OTP Sent Successfully") ) {
                        Intent i = new Intent(OtpActivity.this, OtpActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toasty.error(OtpActivity.this, user.getMessage(), Toast.LENGTH_SHORT, true).show();
                    }
                }
                else{
                    Toasty.error(OtpActivity.this, "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(OtpActivity.this).build();
            }
        });

    }


}
