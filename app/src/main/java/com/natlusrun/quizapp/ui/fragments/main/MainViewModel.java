package com.natlusrun.quizapp.ui.fragments.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

   public MutableLiveData<String> nameData = new MutableLiveData<>();


    void getName(String name){
        nameData.setValue(name);

    }

}