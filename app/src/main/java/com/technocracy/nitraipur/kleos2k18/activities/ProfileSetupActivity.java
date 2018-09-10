package com.technocracy.nitraipur.kleos2k18.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.florent37.viewtooltip.ViewTooltip;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.model.User;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;

import am.appwise.components.ni.NoInternetDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tyrantgit.explosionfield.ExplosionField;

import static maes.tech.intentanim.CustomIntent.customType;

public class ProfileSetupActivity extends AppCompatActivity {
    Button submit;
    ExplosionField mExplosionField;
    EditText firstname, lastname, college, email;
    UserPreferences userPreferences;
    AVLoadingIndicatorView indicatorView;
    CircleImageView circleImageView;
    ImagePicker imagePicker;
    File file;
    ApiEndpoints apiBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
        customType(this, "fadein-to-fadeout");

        userPreferences = new UserPreferences(this);

        firstname = (EditText) findViewById(R.id.firstName);
        lastname = (EditText) findViewById(R.id.lastName);
        college = (EditText)findViewById(R.id.college);
        email = (EditText) findViewById(R.id.email);
        circleImageView = (CircleImageView)findViewById(R.id.drawerImg);

        indicatorView = (AVLoadingIndicatorView)findViewById(R.id.avi);
        indicatorView.hide();

        submit = (Button)findViewById(R.id.submit);
        mExplosionField = ExplosionField.attach2Window(this);
        Slice slice = new Slice(submit);
        slice.setRadius(8f);
        slice.setColor(Color.parseColor("#00BB84"));

        imagePicker = new ImagePicker(this ,
                null,
                imageUri -> {
                    Glide.with(this).load(imageUri).into(circleImageView);
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode,requestCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
    }
    public void pickImage(View view) {
        imagePicker.choosePicture(true);
    }



    public void showViewTooltip(View v, String message){
        ViewTooltip.on(v)
                .align(ViewTooltip.ALIGN.CENTER)
                .position(ViewTooltip.Position.TOP)
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
    public void submit(View v){

        if(!String.valueOf(firstname.getText()).equals("") && !String.valueOf(lastname.getText()).equals("") && !String.valueOf(email.getText()).equals("") ){
            indicatorView.show();
            mExplosionField.explode(v);

            String username = userPreferences.getUsername();
            submit.setEnabled(false);
            apiBase = ApiBase.getClient().create(ApiEndpoints.class);
            Call<User> call = apiBase.fillDetails(username,
                    firstname.getText().toString(),
                    lastname.getText().toString(),
                    email.getText().toString(),
                    college.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if(response.isSuccessful()){
                    if(String.valueOf(response.body().message).equals("User Created Succesfully")){
                        File file = imagePicker.getImageFile();
                        if(file != null) {
                            userPreferences.saveProfileImage(Uri.fromFile(file));
                        }userPreferences.saveProfileImage(userPreferences.getProfileImage());
                        userPreferences.setName(firstname.getText().toString().concat(" "+ lastname.getText().toString()));
                        userPreferences.setLevel("1");

                        Intent i = new Intent(ProfileSetupActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }}else{
                        Toasty.error(ProfileSetupActivity.this, "Some Thing Went Wrong", Toast.LENGTH_SHORT, true).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    call.cancel();
                    NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(ProfileSetupActivity.this).build();
                }
            });

        }
        else{
            showViewTooltip(firstname, "This field can't be empty");
            showViewTooltip(lastname, "This field can't be empty");
            showViewTooltip(email, "This field can't be empty");
        }

    }


}
