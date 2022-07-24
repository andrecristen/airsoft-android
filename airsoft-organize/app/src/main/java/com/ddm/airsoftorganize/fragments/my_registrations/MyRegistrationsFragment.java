package com.ddm.airsoftorganize.fragments.my_registrations;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.adapter.MyEventAdapter;
import com.ddm.airsoftorganize.databinding.FragmentMyRegistrationsBinding;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.EventResponse;
import com.ddm.airsoftorganize.response.FetchEventResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRegistrationsFragment extends Fragment {

    RecyclerView recyclerView;

    private FragmentMyRegistrationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyRegistrationsViewModel accountViewModel = new ViewModelProvider(this).get(MyRegistrationsViewModel.class);
        binding = FragmentMyRegistrationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.myRegistrationsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProgressDialog progress = new ProgressDialog(this.getContext());
        progress.setTitle("Carregando");
        progress.setMessage("Realizando busca das suas inscrições.");
        progress.setCancelable(false);
        progress.show();
        List<EventResponse> eventList = new ArrayList<>();
        String token = UserSession.getInstance(null).token;
        Call<FetchEventResponse> call = new RetrofitInitializer().event().fetchAllEventsMyRegistrations(token, "true");
        call.enqueue(new Callback<FetchEventResponse>() {
            @Override
            public void onResponse(Call<FetchEventResponse> call, Response<FetchEventResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    for (EventResponse event : response.body().getEventos()) {
                        eventList.add(event);
                    }
                    recyclerView.setAdapter(new MyEventAdapter(getActivity(), eventList));
                } else {
                    Toast.makeText(getActivity(), "Não foi possível realizar a busca das inscrições.", Toast.LENGTH_SHORT).show();
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
        binding = null;
    }
}