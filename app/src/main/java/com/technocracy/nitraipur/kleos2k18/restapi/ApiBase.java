package com.technocracy.nitraipur.kleos2k18.restapi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBase {
    //private static final String BASE_URL = "https://kleos2k18.appspot.com/";
    private static final String BASE_URL = "http://192.168.2.5:8000/"; //For Local Testing
    public static Retrofit  retrofit = null;
    private ApiBase() {
    }

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }



}
