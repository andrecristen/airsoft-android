package com.ddm.airsoftorganize;

import static org.junit.Assert.assertEquals;

import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeEventTest {
    private String result;

    @Test
    public void subscribe_event_test(){
        String token = "de9133a2e98b3da31597d31ac65f334b";
        CountDownLatch latch = new CountDownLatch(1);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("time", "9")
                .addFormDataPart("classe", "2")
                .build();
        Call<DefaultResponse> call = new RetrofitInitializer().event().subscribeEvento(requestBody, token);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                latch.countDown();
                if (response.body() != null) {
                        result = "success";
                    }else{
                        result = "failure";
                    }}


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