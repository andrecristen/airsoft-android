package com.ddm.airsoftorganize.fragments.team;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.FindUserTeamActivity;
import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.adapter.EventAdapter;
import com.ddm.airsoftorganize.databinding.FragmentTeamBinding;
import com.ddm.airsoftorganize.models.City;
import com.ddm.airsoftorganize.models.Event;
import com.ddm.airsoftorganize.models.Field;
import com.ddm.airsoftorganize.models.State;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamFragment extends Fragment {

    private FragmentTeamBinding binding;

    Button buttonFindUserTeam;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TeamViewModel teamViewModel =
                new ViewModelProvider(this).get(TeamViewModel.class);

        binding = FragmentTeamBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonFindUserTeam = (Button) view.findViewById(R.id.findUserTeam);
        buttonFindUserTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(view.getContext(), FindUserTeamActivity.class));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}