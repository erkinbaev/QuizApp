package com.natlusrun.quizapp;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.natlusrun.quizapp.data.IHistoryStorage;
import com.natlusrun.quizapp.data.QuizRepository;
import com.natlusrun.quizapp.data.db.QuizDataBase;
import com.natlusrun.quizapp.data.network.QuizApiClient;

public class App extends Application {

    public static QuizApiClient quizApiClient;
    public static QuizRepository repository;
    public static QuizDataBase quizDataBase;


    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();

        repository = new QuizRepository(quizApiClient);

        quizDataBase = Room.databaseBuilder(
                this,
                QuizDataBase.class,
                "QuizDb"
        ).allowMainThreadQueries()
                .build();
    }
}
