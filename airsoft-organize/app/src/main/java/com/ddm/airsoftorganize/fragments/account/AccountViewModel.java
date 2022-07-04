package com.ddm.airsoftorganize.fragments.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Lucas Levi Gonçalves");
    }

    public LiveData<String> getText() {
        return mText;
    }
}