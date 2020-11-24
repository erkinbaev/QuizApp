package com.natlusrun.quizapp.ui.fragments.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {


    public MutableLiveData<String> numberData = new MutableLiveData<>();
    Integer num = 0;

    void increaseNum() {
       num++;
       setNumber();
    }

    void decreaseNum(){
        num--;
        setNumber();
    }

    void setNumber() {
        numberData.setValue(String.valueOf(num));
    }

}