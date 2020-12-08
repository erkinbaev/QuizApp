package com.natlusrun.quizapp.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.gson.Gson;
import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.QuestionModel;
import com.natlusrun.quizapp.data.model.QuizResult;
import com.natlusrun.quizapp.data.model.StaticQuestionModel;
import com.natlusrun.quizapp.data.network.IApiQuizApiClient;
import com.natlusrun.quizapp.ui.adapters.AnswerClickListener;
import com.natlusrun.quizapp.ui.adapters.QuestionRecyclerAdapter;
import com.natlusrun.quizapp.ui.fragments.main.MainFragment;

import java.util.ArrayList;
import java.util.Date;

public class QuestionActivity extends AppCompatActivity implements AnswerClickListener {

    private SeekBar seekBar;
    private RecyclerView qRecyclerView;
    private QuestionRecyclerAdapter questionRecyclerAdapter;
    private static ArrayList<QuestionModel> list;
    private int forAnswer = 0;
    private String categoryStr, difficulty;
    public static String MODEL = "model";
    private int position, amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        init();

        Intent intent = getIntent();

        App.repository.getQuestions(new IApiQuizApiClient.QuestionsCallBack() {
                                        @Override
                                        public void onSuccess(ArrayList<QuestionModel> result) {
                                            questionRecyclerAdapter.setList(result);
                                            list.addAll(result);
                                        }

                                        @Override
                                        public void onFailure(Exception e) {
                                            Log.d("TAG", "onFailure: " + "no");

                                        }
                                    }, intent.getIntExtra(MainFragment.ID, 0),
                intent.getIntExtra(MainFragment.CATEGORY, 0),
                intent.getStringExtra(MainFragment.DIFFICULTY));

        categoryStr = intent.getStringExtra(MainFragment.CATEGORY_STR);
        difficulty = intent.getStringExtra(MainFragment.DIFFICULTY);
        Log.d("TAG", "birinchi: " + categoryStr + "difficulty" + difficulty);

    }

    private void init() {
        seekBar = findViewById(R.id.question_progress_bar);
        qRecyclerView = findViewById(R.id.questions_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        qRecyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(qRecyclerView);
        questionRecyclerAdapter = new QuestionRecyclerAdapter(this);
        qRecyclerView.setAdapter(questionRecyclerAdapter);
    }

//   // private void setSeekBar() {
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            int progressValue = 0;
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                progressValue = progress;
//                questionAmount.setText("" +progressValue);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                questionAmount.setText("" +progressValue);
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                questionAmount.setText("" +progressValue);
//
//            }
//        });
//    }

    @Override
    public void onAnswerClick(boolean b, int adapterPosition) {


        if (b)
            forAnswer++; //передать в резалт актвити

        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                position = adapterPosition + 1;
                qRecyclerView.scrollToPosition(position);
            }
        }.start();

    }

    @Override
    public void openActivity() {

        Toast.makeText(this, "super", Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        QuizResult qr = new QuizResult(categoryStr,
                difficulty,
                forAnswer,
                new Date(System.currentTimeMillis()),
                list,
                getIntent().getIntExtra(MainFragment.ID, 44)
        );

        Log.d("TAG", "QA - difficulty: " + qr.getDifficulty());
        Log.d("TAG", "QA - category: " + qr.getCategory());
        Log.d("TAG", "QA - correctanswer: " + qr.getCorrectAnswerResult());

        intent.putExtra(MODEL, gson.toJson(qr));
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (position > 0)
            qRecyclerView.scrollToPosition(position--);
        else
            super.onBackPressed();

    }
}
