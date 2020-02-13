package com.example.transmusicales.ui.artists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArtistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ArtistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Voici les artistes des Trans Musicales !");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
