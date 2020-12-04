package com.natlusrun.quizapp;

public interface IBaseCallBack<T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}
