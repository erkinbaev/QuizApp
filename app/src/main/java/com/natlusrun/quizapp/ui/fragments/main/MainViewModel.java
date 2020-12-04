package com.natlusrun.quizapp.ui.fragments.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.data.model.CategoryModel;
import com.natlusrun.quizapp.data.network.IApiQuizApiClient;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<CategoryModel>> listCategory = new MutableLiveData<>();
    ArrayList<String> listStrCategory = new ArrayList<>();

    public MutableLiveData<String> numberData = new MutableLiveData<>();
    Integer num = 0;

    void increaseNum() {
        num++;
        setNumber();
    }

    void decreaseNum() {
        num--;
        setNumber();
    }

    void setNumber() {
        numberData.setValue(String.valueOf(num));
    }

    public void getCategories() {
        App.quizApiClient.getCategory(new IApiQuizApiClient.CategoryCallback() {
            @Override
            public void onFailure(Exception e) {
            }

            @Override
            public void onSuccess(ArrayList<CategoryModel> result) {
                for (int i = 0; i < result.size(); i++) {
                    listStrCategory.add(result.get(i).getName());
                }
                listCategory.setValue(result);
            }
        });
    }

}