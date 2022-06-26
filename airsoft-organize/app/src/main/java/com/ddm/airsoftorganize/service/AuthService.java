package com.ddm.airsoftorganize.service;

import com.ddm.airsoftorganize.models.AuthResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("api/v1/auth")
    Call<AuthResponse> executeAuth(@Body RequestBody auth);

}


