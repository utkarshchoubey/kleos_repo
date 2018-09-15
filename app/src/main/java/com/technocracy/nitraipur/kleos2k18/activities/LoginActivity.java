package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppCompatActivity {
 Button signup, signupPage, loginPage;
 EditText phone, pass, confirmPass;
 TextInputLayout passH,phoneH,confirmH;
 AVLoadingIndicatorView indicatorView;
 UserPreferences userPreferences;
 ApiEndpoints apiBase;
    AnimatedGradientTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        customType(this, "fadein-to-fadeout");

        userPreferences = new UserPreferences(this);

        textView = (AnimatedGradientTextView)findViewById(R.id.kleos);
        textView.setTextSize(getResources().getDimension(R.dimen.textsize));

        signupPage = (Button)findViewById(R.id.signupButton);
        loginPage = (Button)findViewById(R.id.loginButton);

        indicatorView = (AVLoadingIndicatorView)findViewById(R.id.avi);
        indicatorView.hide();

        phone = (EditText)findViewById(R.id.phoneNo);
        pass = (EditText)findViewById(R.id.pass);
        confirmPass = (EditText)findViewById(R.id.confirmPass);

        passH = (TextInputLayout)findViewById(R.id.passHint);
        confirmH = (TextInputLayout)findViewById(R.id.confirmHint);
        phoneH = (TextInputLayout)findViewById(R.id.phoneHint);

        signupPage.setTextColor(Color.parseColor("#FFFFFF"));
        loginPage.setTextColor(Color.parseColor("#89FFFFFF"));

        signup = (Button)findViewById(R.id.signup);

        Slice slice = new Slice(signup);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));

        apiBase = ApiBase.getClient().create(ApiEndpoints.class);


    }

    public void showViewTooltip(View v, String message){
        ViewTooltip.on(v)
                .align(ViewTooltip.ALIGN.CENTER)
                .position(ViewTooltip.Position.LEFT)
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

    public void signupPage(View view) {
        indicatorView.hide();
        signupPage.setTextColor(Color.parseColor("#FFFFFF"));
        loginPage.setTextColor(Color.parseColor("#89FFFFFF"));

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }

        confirmH.setVisibility(View.INVISIBLE);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(phoneH);
        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(passH);
        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(signup);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        phone.setText("");
                        pass.setText("");
                        confirmPass.setText("");
                        confirmH.setVisibility(View.VISIBLE);
                        signup.setText("Sign me Up");
                    }
                }, 600);

            }
        });


        YoYo.with(Techniques.SlideInRight)
                .delay(500)
                .duration(500)
                .playOn(phoneH);

        YoYo.with(Techniques.SlideInRight)
                .delay(500)
                .duration(500)
                .playOn(passH);

        YoYo.with(Techniques.SlideInRight)
                .delay(500)
                .duration(500)
                .playOn(confirmH);
        YoYo.with(Techniques.FadeIn)
                .delay(500)
                .duration(500)
                .playOn(signup);


    }

    public void loginPage(View view) {

        indicatorView.hide();
        loginPage.setTextColor(Color.parseColor("#FFFFFF"));
        signupPage.setTextColor(Color.parseColor("#89FFFFFF"));

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(phoneH);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(passH);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(confirmH);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .playOn(signup);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        phone.setText("");
                        pass.setText("");
                        confirmPass.setText("");
                        signup.setText("Log me In");
                    }
                }, 600);

            }
        });


        YoYo.with(Techniques.SlideInRight)
                .delay(500)
                .duration(500)
                .playOn(phoneH);
        YoYo.with(Techniques.SlideInRight)
                .delay(500)
                .duration(500)
                .playOn(passH);

        YoYo.with(Techniques.FadeIn)
                .delay(500)
                .duration(500)
                .playOn(signup);




    }

    public void next(View view) {

        if(String.valueOf(this.signup.getText()).equals("Sign me Up")){

            if(!String.valueOf(pass.getText()).equals("") && !String.valueOf(phone.getText()).equals("") && !String.valueOf(confirmPass.getText()).equals("")){
                if(String.valueOf(phone.getText()).length() != 10){
                    YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                    showViewTooltip(phone,"Enter a valid Phone Number");
                }
                else if(String.valueOf(pass.getText()).length() < 8 && String.valueOf(confirmPass.getText()).length() < 8){
                    YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                    showViewTooltip(confirmPass,"Password must be of 8 character");
                    showViewTooltip(pass,"Password must be of 8 character");
                }
                else if(!String.valueOf(pass.getText()).equals(String.valueOf(confirmPass.getText()))){
                    YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                    showViewTooltip(confirmPass,"Password Didn't match");
                    showViewTooltip(pass,"Password Didn't match");
                    confirmPass.setText("");
                    pass.setText("");
                }
                else{
                    YoYo.with(Techniques.FadeOut).duration(500).playOn(view);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    signup.setVisibility(View.INVISIBLE);
                                    indicatorView.show();
                                }
                            }, 600);

                        }
                    });

                    loginPage.setEnabled(false);
                    signupPage.setEnabled(false);

                    String phoneNo = "+91"+String.valueOf(phone.getText());
                    String password = String.valueOf(pass.getText());

                    Call<User> call = apiBase.createUser(phoneNo,password);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {

                            Log.i("Message", String.valueOf(response.body().message));
                            if(String.valueOf(response.body().message).equals("OTP Sent Successfully")) {
                                userPreferences.setUsername(phoneNo);
                                Intent i = new Intent(LoginActivity.this, OtpActivity.class);
                                startActivity(i);
                                finish();

                            }else{
                                Toasty.error(LoginActivity.this, "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                                indicatorView.hide();
                                YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                                signup.setVisibility(View.VISIBLE);
                                loginPage.setEnabled(true);
                                signupPage.setEnabled(true);
                            }

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            indicatorView.hide();
                            YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                            signup.setVisibility(View.VISIBLE);
                            loginPage.setEnabled(true);
                            signupPage.setEnabled(true);
                            NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(LoginActivity.this).build();
                        }
                    });

                }

            }
            else
                {
                    YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                    showViewTooltip(phone,"Enter a valid Phone Number");
                    showViewTooltip(confirmPass,"Enter a valid Password");
                    showViewTooltip(pass, "Enter a valid Password");

            }

        }
        else if(String.valueOf(this.signup.getText()).equals("Log me In")){

            if(!String.valueOf(pass.getText()).equals("") && !String.valueOf(phone.getText()).equals("")){
                if(String.valueOf(phone.getText()).length() != 10){
                    YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                    showViewTooltip(phone,"Enter a valid Phone Number");
                }
                else if(String.valueOf(pass.getText()).length() < 8){
                    YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                    showViewTooltip(pass,"Password must be of 8 character");
                }
                else{
                    YoYo.with(Techniques.FadeOut).duration(500).playOn(view);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    signup.setVisibility(View.INVISIBLE);
                                    indicatorView.show();
                                }
                            }, 600);

                        }
                    });
                    loginPage.setEnabled(false);
                    signupPage.setEnabled(false);

                    final String phoneNo = "+91"+String.valueOf(phone.getText());
                    final String password = String.valueOf(pass.getText());

                    Call<User> call = apiBase.loginUser(phoneNo,password);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                            if(response.isSuccessful()) {
                               if(!String.valueOf(response.body().key).equals("")) {
                                   userPreferences.setUsername(phoneNo);
                                   userPreferences.setPassword(password);

                                   Call<User> userCall=apiBase.getDetails(userPreferences.getUsername());
                                   userCall.enqueue(new Callback<User>() {
                                       @Override
                                       public void onResponse(Call<User> call, Response<User> response) {
                                           if(response.isSuccessful()){
                                               if(!String.valueOf(response.body().email).equals("")){
                                                   userPreferences.setName(response.body().firstName.concat(" "+ response.body().lastName));
                                                   userPreferences.setLevel(response.body().level);

                                                   Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                                   startActivity(i);
                                                   finish();
                                               }
                                           }else {
                                               indicatorView.hide();
                                               YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                                               signup.setVisibility(View.VISIBLE);
                                               loginPage.setEnabled(true);
                                               signupPage.setEnabled(true);
                                           }
                                       }

                                       @Override
                                       public void onFailure(Call<User> call, Throwable t) {
                                           indicatorView.hide();
                                           YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                                           signup.setVisibility(View.VISIBLE);
                                           loginPage.setEnabled(true);
                                           signupPage.setEnabled(true);
                                           NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(LoginActivity.this).build();
                                       }
                                   });

                               }else{
                                   indicatorView.hide();
                                   Toasty.info(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT, true).show();
                                   YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                                   signup.setVisibility(View.VISIBLE);
                                   loginPage.setEnabled(true);
                                   signupPage.setEnabled(true);
                               }
                            }
                            else{
                                indicatorView.hide();
                                YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                                signup.setVisibility(View.VISIBLE);
                                loginPage.setEnabled(true);
                                signupPage.setEnabled(true);
                                try {
                                    Toasty.error(LoginActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT, true).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            indicatorView.hide();
                            YoYo.with(Techniques.FadeIn).duration(500).playOn(view);
                            signup.setVisibility(View.VISIBLE);
                            loginPage.setEnabled(true);
                            signupPage.setEnabled(true);
                            NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(LoginActivity.this).build();
                        }
                    });

                }

            }
            else
            {   YoYo.with(Techniques.Shake).duration(500).playOn(signup);
                showViewTooltip(phone,"Enter a valid Phone Number");
                showViewTooltip(pass, "Enter a valid Password");
            }


        }
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
        Toasty.info(this, "Please click BACK again to exit", Toast.LENGTH_SHORT,true).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
