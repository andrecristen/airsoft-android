package com.ddm.airsoftorganize.retrofit.service;

import com.ddm.airsoftorganize.response.FetchUserTeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserTeamService {

    @GET("api/v1/userTeamList")
    Call<FetchUserTeamResponse> fetchAllUserTeams(@Query("token") String tokenUser);

}
