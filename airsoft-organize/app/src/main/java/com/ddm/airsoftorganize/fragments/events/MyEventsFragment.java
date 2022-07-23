package com.ddm.airsoftorganize.fragments.events;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.EventDetailActivity;
import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.adapter.EventAdapter;
import com.ddm.airsoftorganize.models.City;
import com.ddm.airsoftorganize.models.Event;
import com.ddm.airsoftorganize.models.Field;
import com.ddm.airsoftorganize.models.State;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.EventResponse;
import com.ddm.airsoftorganize.response.FetchEventResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEventsFragment extends Fragment {

    RecyclerView recyclerView;

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
        ProgressDialog progress = new ProgressDialog(this.getContext());
        progress.setTitle("Carregando..");
        progress.setMessage("Realizando busca de eventos...");
        progress.setCancelable(false);
        progress.show();
        List<EventResponse> eventList = new ArrayList<>();
        String token = UserSession.getInstance(null).token;
        Call<FetchEventResponse> call = new RetrofitInitializer().event().fetchAllEvents(token);
        call.enqueue(new Callback<FetchEventResponse>() {
            @Override
            public void onResponse(Call<FetchEventResponse> call, Response<FetchEventResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    for (EventResponse event : response.body().getEventos()) {
                        eventList.add(event);
                    }
                    recyclerView.setAdapter(new EventAdapter(getActivity(), eventList));
                } else {
                    Toast.makeText(getActivity(), "Não foi possível realizar a busca de eventos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchEventResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}