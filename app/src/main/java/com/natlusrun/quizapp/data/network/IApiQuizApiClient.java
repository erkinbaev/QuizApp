package com.natlusrun.quizapp.data.network;

import com.natlusrun.quizapp.IBaseCallBack;
import com.natlusrun.quizapp.data.model.CategoryModel;
import com.natlusrun.quizapp.data.model.QuestionModel;

import java.util.ArrayList;

public interface IApiQuizApiClient {

    void getQuestions(QuestionsCallBack callBack, int amount, int category, String difficulty);

    void getCategory(CategoryCallback categoryCallback);

    interface QuestionsCallBack extends IBaseCallBack<ArrayList<QuestionModel>> {
        @Override
        void onSuccess(ArrayList<QuestionModel> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoryCallback extends IBaseCallBack<ArrayList<CategoryModel>> {

        @Override
        void onFailure(Exception e);

        @Override
        void onSuccess(ArrayList<CategoryModel> result);
    }


}
