package com.natlusrun.quizapp.data.network;

import android.util.Log;

import com.natlusrun.quizapp.data.model.CategoryResponse;
import com.natlusrun.quizapp.data.model.QuestionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IApiQuizApiClient {

    private final String TAG = "tag";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    @Override
    public void getQuestions(QuestionsCallBack callBack, int amount, int category, String difficulty) {
        Call<QuestionResponse> call = apiInterface.getQuestions(amount, category, difficulty);
        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: success" + response.body().toString());
                    callBack.onSuccess(response.body().getQuestions());
                } else {
                    callBack.onFailure(new Exception("Response is Empty" + response.body()));
                    Log.d(TAG, "onResponse: failure" + response.message() + "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: failure" + t + "" + t.getMessage());
            }
        });
    }

    @Override
    public void getCategory(CategoryCallback categoryCallback) {
        Call<CategoryResponse> call = apiInterface.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    categoryCallback.onSuccess(response.body().getList());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }
}
