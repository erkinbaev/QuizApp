package com.natlusrun.quizapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.natlusrun.quizapp.data.IHistoryStorage;
import com.natlusrun.quizapp.data.PreferenceUtils;
import com.natlusrun.quizapp.data.QuizRepository;
import com.natlusrun.quizapp.data.db.QuizDataBase;
import com.natlusrun.quizapp.data.network.QuizApiClient;

import java.util.Objects;

public class App extends Application {

    public static QuizApiClient quizApiClient;
    public static QuizRepository repository;
    public static QuizDataBase quizDataBase;
    public static final String SHARED = "shared";

    public static SharedPreferences sp;


    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();

        repository = new QuizRepository(quizApiClient);

        sp = Objects.requireNonNull(getSharedPreferences(SHARED, Context.MODE_PRIVATE));

        quizDataBase = Room.databaseBuilder(
                this,
                QuizDataBase.class,
                "QuizDb"
        ).allowMainThreadQueries()
                .build();
    }
}
