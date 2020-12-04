package com.natlusrun.quizapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionResponse {

    @SerializedName("response_code")
    @Expose
    private int responseCode;

    @SerializedName("results")
    @Expose
    private ArrayList<QuestionModel> questions;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.questions = questions;
    }
}
