package com.ddm.airsoftorganize.ui.event_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventHistoryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EventHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("EventHistory fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}