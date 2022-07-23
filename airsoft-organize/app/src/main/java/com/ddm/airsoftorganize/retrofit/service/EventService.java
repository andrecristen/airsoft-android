package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.response.EventDetailResponse;
import com.ddm.airsoftorganize.response.FetchEventResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventService {

    @GET("api/v1/eventosList")
    Call<FetchEventResponse> fetchAllEvents(@Query("token") String tokenUser);

    @GET("api/v1/eventoDetails/{id}")
    Call<EventDetailResponse> eventDetails(@Path("id") String id, @Query("token") String tokenUser);

    @POST("api/v1/subscribeEvento")
    Call<DefaultResponse> subscribeEvento(@Body RequestBody subscribe, @Query("token") String tokenUser);

    @POST("api/v1/unsubscribeEvento")
    Call<DefaultResponse> unsubscribeEvento(@Body RequestBody subscribe, @Query("token") String tokenUser);

}

