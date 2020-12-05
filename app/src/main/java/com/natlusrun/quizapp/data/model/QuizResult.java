package com.natlusrun.quizapp.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizResult {

    private String category;
    private String difficulty;
    private int correctAnswerResult;
    private Date createdAt;
    private ArrayList<QuestionModel> questions;

    public QuizResult(String category, String difficulty, int correctAnswerResult, Date createdAt, ArrayList<QuestionModel> questions) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.createdAt = createdAt;
        this.questions = questions;
    }
}
