package com.ddm.airsoftorganize.service;

import com.ddm.airsoftorganize.models.AuthResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/v1/createUser")
    Call<AuthResponse> executeCreateUser(@Body RequestBody createUser);

}

