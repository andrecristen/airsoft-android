package com.ddm.airsoftorganize.fragments.team;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TeamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("team fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}