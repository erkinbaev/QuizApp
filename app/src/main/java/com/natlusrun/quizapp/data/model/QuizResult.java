package com.natlusrun.quizapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.natlusrun.quizapp.data.db.converter.DateConverter;
import com.natlusrun.quizapp.data.db.converter.QuestionConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correctAnswerResult")
    private int correctAnswerResult;
    @TypeConverters(DateConverter.class)
    private Date createdAt;
    @TypeConverters(QuestionConverter.class)
    private ArrayList<QuestionModel> questions;
    @ColumnInfo(name = "size")
    int size;
    //private int count;

    public QuizResult(String category, String difficulty, int correctAnswerResult, Date createdAt, ArrayList<QuestionModel> questions, int size) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.createdAt = createdAt;
        this.questions = questions;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswerResult() {
        return correctAnswerResult;
    }

    public void setCorrectAnswerResult(int correctAnswerResult) {
        this.correctAnswerResult = correctAnswerResult;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.questions = questions;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
