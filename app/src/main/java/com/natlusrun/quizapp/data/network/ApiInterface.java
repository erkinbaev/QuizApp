package com.natlusrun.quizapp.data.network;

import com.natlusrun.quizapp.data.model.CategoryResponse;
import com.natlusrun.quizapp.data.model.QuestionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php")
    Call<QuestionResponse> getQuestions(
            @Query("amount") int amount,
            @Query("category") int category,
            @Query("difficulty") String difficulty);

    @GET("api_category.php")
    Call<CategoryResponse> getCategories();
}
