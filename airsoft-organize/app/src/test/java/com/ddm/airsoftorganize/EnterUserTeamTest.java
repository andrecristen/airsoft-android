package com.ddm.airsoftorganize;

import org.junit.Test;

import static org.junit.Assert.*;

import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.response.FetchUserTeamResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.concurrent.CountDownLatch;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterUserTeamTest {
    private String result;

    @Test
    public void enter_userTeam_test(){
        String token = "de9133a2e98b3da31597d31ac65f334b";
        CountDownLatch latch = new CountDownLatch(1);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("equipe", "3")
                .build();
        Call<DefaultResponse> call = new RetrofitInitializer().userTeam().enterUserTeam(token, requestBody);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                latch.countDown();
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("true")) {
                        result = "success";
                    }else{
                        result = "failure";
                }}
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
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