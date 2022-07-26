package com.ddm.airsoftorganize;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


import com.ddm.airsoftorganize.response.FetchUserTeamResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUserTeamTest {
    private String result;

    @Test
    public void fetch_userTeam_test(){
           String token = "de9133a2e98b3da31597d31ac65f334b";
           Call<FetchUserTeamResponse> call = new RetrofitInitializer().userTeam().fetchAllUserTeams(token);
           CountDownLatch latch = new CountDownLatch(1);
           call.enqueue(new Callback<FetchUserTeamResponse>() {
           @Override
            public void onResponse(Call<FetchUserTeamResponse> call, Response<FetchUserTeamResponse> response) {
                latch.countDown();
                if (response.isSuccessful()) {
                    result = "success";

                }else{
                    result = "failure";
                    System.out.println("Failure");
                }
            }

            @Override
            public void onFailure(Call<FetchUserTeamResponse> call, Throwable t) {
                System.out.println("Failure");
                result = "failure";
                latch.countDown();
         }
        });
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(result);
        assertEquals("success", result);

    }
}