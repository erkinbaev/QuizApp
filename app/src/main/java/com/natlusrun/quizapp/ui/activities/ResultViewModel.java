package com.natlusrun.quizapp.ui.activities;

import androidx.lifecycle.ViewModel;

import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.data.model.QuizResult;

public class ResultViewModel extends ViewModel {

    public void saveResultToDb(QuizResult quizResult){
          App.quizDataBase.quizDao().insert(quizResult);

    }
}
