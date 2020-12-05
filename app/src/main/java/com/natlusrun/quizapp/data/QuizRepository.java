package com.natlusrun.quizapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.natlusrun.quizapp.data.model.QuestionModel;
import com.natlusrun.quizapp.data.model.QuizResult;
import com.natlusrun.quizapp.data.network.IApiQuizApiClient;

import java.util.ArrayList;
import java.util.Collections;

public class QuizRepository implements IApiQuizApiClient, IHistoryStorage {


    private IApiQuizApiClient iApiQuizApiClient;

    public QuizRepository(IApiQuizApiClient iApiQuizApiClient) {
        this.iApiQuizApiClient = iApiQuizApiClient;
    }

    @Override
    public void getQuestions(QuestionsCallBack callBack, int amount, int category, String difficulty) {
        iApiQuizApiClient.getQuestions(new QuestionsCallBack() {
            @Override
            public void onSuccess(ArrayList<QuestionModel> result) {
                for (int i = 0; i < result.size(); i++) {
                    QuestionModel model = result.get(i);
                    ArrayList<String> list = new ArrayList<>(model.getIncorrectAnswers());
                    list.add(model.getCorrectAnswer());
                    Collections.shuffle(list);
                    model.setIncorrectAnswers(list);
                }
                Log.d("TAG", "App onSuccess: " + result.size());
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
                Log.d("TAG", "App onFailure: ");
            }
        }, amount, category, difficulty);
    }

    @Override
    public void getCategory(CategoryCallback categoryCallback) {

    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<ArrayList<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
