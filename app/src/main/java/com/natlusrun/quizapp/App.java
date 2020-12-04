package com.natlusrun.quizapp;

import android.app.Application;

import com.natlusrun.quizapp.data.network.QuizApiClient;

public class App extends Application {

    public static QuizApiClient quizApiClient;
    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();
    }
}
