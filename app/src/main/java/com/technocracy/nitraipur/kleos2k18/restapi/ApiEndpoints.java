package com.technocracy.nitraipur.kleos2k18.restapi;

import com.technocracy.nitraipur.kleos2k18.model.Question;
import com.technocracy.nitraipur.kleos2k18.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiEndpoints {
    @FormUrlEncoded
    @POST("user/api/create/new")
    Call<User> createUser(@Field("username")String phone, @Field("password") String pass);

    @FormUrlEncoded
    @POST("user/api/rest-auth/login/")
    Call<User> loginUser(@Field("username") String phone, @Field("password") String pass);

    @FormUrlEncoded
    @POST("user/api/create/otp")
    Call<User> otpVerification(@Field("username")String phone, @Field("otp") String otp);

    @FormUrlEncoded
    @POST("user/api/create/otpverified")
    Call<User> fillDetails(@Field("username") String phone,
                           @Field("first_name") String first_name,
                           @Field("last_name") String last_name,
                           @Field("email") String email,
                           @Field("college") String college);

    @GET("user/api/retrieve/{phonenumber}")
    Call<User> getDetails(@Path("phonenumber") String phone);

    @Multipart
    @POST("user/api/create/uploadPic")
    Call<User> uploadPic(@Part("username") RequestBody phone, @Part MultipartBody.Part image);

    @GET("user/api/leaderboard")
    Call<List<User>> getLeaderboard();

    @GET("questions/api/{id}")
    Call<Question> getQuestionbyId(@Path("id") String id);
}
