package com.natlusrun.quizapp.data.db.converter;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.natlusrun.quizapp.data.model.QuestionModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class DateConverter {


    @TypeConverter
    public static Long fromDate(@Nullable Date date){
        if (date == null) return  null;
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(@Nullable Long timeStump){
        if(timeStump == null) return  null;
        return new Date(timeStump);
    }

}

