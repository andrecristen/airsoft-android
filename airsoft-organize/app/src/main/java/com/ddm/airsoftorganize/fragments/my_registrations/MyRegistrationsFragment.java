package com.ddm.airsoftorganize.fragments.my_registrations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.adapter.EventAdapter;
import com.ddm.airsoftorganize.databinding.FragmentMyRegistrationsBinding;
import com.ddm.airsoftorganize.models.City;
import com.ddm.airsoftorganize.models.Event;
import com.ddm.airsoftorganize.models.Field;
import com.ddm.airsoftorganize.models.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<Event> eventList = new ArrayList<>();
        State state = new State("1","Santa Catarina", "SC");
        City city = new City("1", "Presidente Getúlio", state);
        Field field = new Field("1", "COMBAT HILL", city);
        Date date = new Date();
        eventList.add(new Event("1",
                "Evento COMBAT HILL",
                date,
                date,
                "Regras padrão",
                "R$ 10,00",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/7c/f0/75/airsoft-field.jpg",field));
        eventList.add(new Event("1",
                "Evento COMBAT HILL",
                date,
                date,
                "Regras padrão",
                "R$ 10,00",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/7c/f0/75/airsoft-field.jpg",field));
        eventList.add(new Event("1",
                "Evento COMBAT HILL",
                date,
                date,
                "Regras padrão",
                "R$ 10,00",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/7c/f0/75/airsoft-field.jpg",field));
        eventList.add(new Event("1",
                "Evento COMBAT HILL",
                date,
                date,
                "Regras padrão",
                "R$ 10,00",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/7c/f0/75/airsoft-field.jpg",field));

        recyclerView.setAdapter(new EventAdapter(getActivity(), eventList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}