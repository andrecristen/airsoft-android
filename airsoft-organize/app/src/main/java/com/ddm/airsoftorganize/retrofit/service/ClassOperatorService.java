package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.FetchClassOperatorResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClassOperatorService {

    @GET("api/v1/classesList")
    Call<FetchClassOperatorResponse> fetchAllClassOperator();

}
