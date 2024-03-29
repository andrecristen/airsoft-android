package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.AuthResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("api/v1/auth")
    Call<AuthResponse> executeAuth(@Body RequestBody auth);

}


