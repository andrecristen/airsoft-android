package com.ddm.airsoftorganize.retrofit;

import com.ddm.airsoftorganize.service.AuthService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder().baseUrl("https://airsoftorganize.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
    }

    public AuthService auth() {
        return retrofit.create(AuthService.class);
    }

}


