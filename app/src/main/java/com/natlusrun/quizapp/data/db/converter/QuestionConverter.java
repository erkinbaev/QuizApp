package com.natlusrun.quizapp.data.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.natlusrun.quizapp.data.model.QuestionModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionConverter {

    @TypeConverter
    public static String toRaw(@Nullable ArrayList<QuestionModel> questions) {
        if(questions == null) return null;
        Gson gson = new Gson();

        return gson.toJson(questions);
    }

    @TypeConverter
    public static ArrayList<QuestionModel> fromRaw(@Nullable String raw) {
        if(raw == null) return null;
        Gson gson = new Gson();

        Type type = new TypeToken<ArrayList<QuestionModel>>() {

        }.getType();
        return gson.fromJson(raw, type);
    }
}
