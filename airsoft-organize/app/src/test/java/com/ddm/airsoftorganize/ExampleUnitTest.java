package com.ddm.airsoftorganize;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.Toast;

import com.ddm.airsoftorganize.adapter.UserTeamAdapter;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.FetchUserTeamResponse;
import com.ddm.airsoftorganize.response.UserTeamResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void fetch_userTeam_test() {
        String token = UserSession.getInstance(null).token;
        Call<FetchUserTeamResponse> call = new RetrofitInitializer().userTeam().fetchAllUserTeams(token);

    }
}