package com.natlusrun.quizapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.natlusrun.quizapp.data.model.QuizResult;

@Database(entities = QuizResult.class, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
