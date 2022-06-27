package com.ddm.airsoftorganize.ui.my_events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.adapter.EventAdapter;
import com.ddm.airsoftorganize.models.Event;
import com.ddm.airsoftorganize.response.FetchEventResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEventsFragment extends Fragment {

    RecyclerView recyclerView;
    List<Event> eventList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<FetchEventResponse> call = new RetrofitInitializer().event().fetchAllEvents();
        call.enqueue(new Callback<FetchEventResponse>() {
            @Override
            public void onResponse(Call<FetchEventResponse> call, Response<FetchEventResponse> response) {
                if(response.isSuccessful()){
                    eventList=response.body().getEventList();
                    recyclerView.setAdapter(new EventAdapter(getActivity(), eventList));
                }else{
                    Toast.makeText(getActivity(), response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchEventResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}