package com.natlusrun.quizapp;

import android.app.Application;

import com.natlusrun.quizapp.data.IHistoryStorage;
import com.natlusrun.quizapp.data.QuizRepository;
import com.natlusrun.quizapp.data.network.QuizApiClient;

public class App extends Application {

    public static QuizApiClient quizApiClient;
    public static QuizRepository repository;


    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();

        repository = new QuizRepository(quizApiClient);

    }
}
