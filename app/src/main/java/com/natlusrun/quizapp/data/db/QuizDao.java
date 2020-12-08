package com.natlusrun.quizapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.natlusrun.quizapp.data.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insert(QuizResult quizResult);

    @Query("SELECT * FROM quizresult")
    List<QuizResult> getAll();

    @Delete
    void delete(QuizResult quizResult);

    @Query("DELETE FROM quizresult WHERE id = :id")
    void deleteById(int id);

    @Update
    void update(QuizResult quizResult);
}
