package com.ddm.airsoftorganize.service;

import com.ddm.airsoftorganize.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserInterface {
    @// TODO: 26/06/2022 to ocm mt sono pra conseguir pensar em alguima coisa vou ficar fazendo as tela agr
    @POST("{id}/json")
    Call<User> select(@Path("id") int id);


}
