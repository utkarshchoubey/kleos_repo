package com.technocracy.nitraipur.kleos2k18.restapi;

import com.technocracy.nitraipur.kleos2k18.model.User.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndpoints {

    @POST("user/api/create/")
    Call<User> createUser(@Body User user);

    @POST("user/api/rest-auth/login")
    Call<User> loginUser(@Body User user);

    @POST("user/api/create/otp")
    Call<User> otpVerification(@Body User user);

    @POST("user/api/create/otpverified")
    Call<User> fillDetails(@Body User user);
}
