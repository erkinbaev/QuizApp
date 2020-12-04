package com.natlusrun.quizapp.data.model;

public class StaticQuestionModel {

    public String question;
    public String type;

    public StaticQuestionModel(String question, String type) {
        this.type = type;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
