package com.natlusrun.quizapp.data;

import androidx.lifecycle.LiveData;

import com.natlusrun.quizapp.data.model.QuizResult;

import java.util.ArrayList;

public interface IHistoryStorage {

    QuizResult getQuizResult (int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<ArrayList<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();
}
