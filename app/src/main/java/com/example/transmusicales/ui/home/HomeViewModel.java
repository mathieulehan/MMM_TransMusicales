package com.example.transmusicales.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("\nLe Festival Transmusicales 2020 se déroulera en décembre prochain (dates encore non confirmées) à Rennes.\n\n" +
                "Salles concernées : La Cité - L'aire Libre - Le Triangle - L'opéra - Le Parc Expo.\n\n" +
                "Au programme du Festival Transmusicales 2020, découvrez de nombreux artistes et groupes de musique en concert à Rennes.\n\n" +
                "Cette application vous permet de vous renseigner davantage sur les artistes qui y seront présents.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}