package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.response.FetchUserTeamResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserTeamService {

    @GET("api/v1/userTeamList")
    Call<FetchUserTeamResponse> fetchAllUserTeams(@Query("token") String tokenUser);

    @POST("api/v1/enterUserTeam")
    Call<DefaultResponse> enterUserTeam(@Query("token") String tokenUser, @Body RequestBody auth);

}
