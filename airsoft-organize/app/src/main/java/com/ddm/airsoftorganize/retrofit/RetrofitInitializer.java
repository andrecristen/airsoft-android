package com.ddm.airsoftorganize.retrofit;

import com.ddm.airsoftorganize.service.AuthService;
import com.ddm.airsoftorganize.service.EventService;
import com.ddm.airsoftorganize.service.UserService;

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

    public UserService user(){
        return retrofit.create(UserService.class);
    }

    public AuthService auth() {
        return retrofit.create(AuthService.class);
    }

    public EventService event() {
        return retrofit.create(EventService.class);
    }

}


