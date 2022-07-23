package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.FetchEventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventService {

    @GET("api/v1/eventosList")
    Call<FetchEventResponse> fetchAllEvents(@Query("token") String tokenUser);

}

