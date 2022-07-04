package com.ddm.airsoftorganize.fragments.my_registrations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRegistrationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRegistrationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("MyRegistrations fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}