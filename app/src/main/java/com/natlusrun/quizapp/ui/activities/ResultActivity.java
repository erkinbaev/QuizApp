package com.natlusrun.quizapp.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.QuestionModel;
import com.natlusrun.quizapp.data.model.QuizResult;
import com.natlusrun.quizapp.databinding.ActivityResultBinding;
import com.natlusrun.quizapp.ui.fragments.settings.SettingsFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = App.sp;

        switch (sp.getInt(SettingsFragment.THEME, 22)) {
            case 0:
                setTheme(R.style.Light);
                break;
            case 1:
                setTheme(R.style.Dark);
                break;
            case 2:
                setTheme(R.style.Silver);
                break;
            case 3:
                setTheme(R.style.Gold);
                break;
            case 4:
                setTheme(R.style.Android);
                break;
        }

        binding = ActivityResultBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        Gson gson = new Gson();
        Type type = new TypeToken<QuizResult>() {
        }.getType();
        String model = getIntent().getStringExtra(QuestionActivity.MODEL);
        QuizResult qr = gson.fromJson(model, type);

        Log.d("TAG", "difficulty: " + qr.getDifficulty());
        Log.d("TAG", "category: " + qr.getCategory());
        Log.d("TAG", "correctanswer: " + qr.getCorrectAnswerResult());

        binding.difficulty.setText(qr.getDifficulty());
        binding.category.setText(qr.getCategory());
        binding.correctAnswersTv.setText(String.valueOf(qr.getCorrectAnswerResult()));


        binding.finish.setOnClickListener(v -> {
            App.quizDataBase.quizDao().insert(qr);
            finish();
        });
    }
}
