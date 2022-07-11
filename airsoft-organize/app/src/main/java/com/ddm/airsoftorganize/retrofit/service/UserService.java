package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/v1/createUser")
    Call<DefaultResponse> executeCreateUser(@Body RequestBody createUser);

}

