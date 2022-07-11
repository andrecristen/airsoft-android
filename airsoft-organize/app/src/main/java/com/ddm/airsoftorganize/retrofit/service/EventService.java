package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.FetchEventResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {

    @GET("api/v1/eventosList?token=de9133a2e98b3da31597d31ac65f334b")
    Call<FetchEventResponse> fetchAllEvents();

}

