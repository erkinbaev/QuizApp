package com.natlusrun.quizapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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

    private RecyclerView qRecyclerView;
    private QuestionRecyclerAdapter questionRecyclerAdapter;
    private static ArrayList<QuestionModel> list;
    private int forAnswer = 0;
    public static String MODEL = "model";
    private int position;

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
                                            Log.d("TAG", "onSuccess1: " + result.size());
                                            Log.d("TAG", "onSuccess2: " + result.get(0).getType());
                                        }

                                        @Override
                                        public void onFailure(Exception e) {
                                            Log.d("TAG", "onFailure: " + "no");

                                        }
                                    }, intent.getIntExtra(MainFragment.ID, 0),
                intent.getIntExtra(MainFragment.CATEGORY, 0),
                intent.getStringExtra(MainFragment.DIFFICULTY));

    }

    private void init() {
        qRecyclerView = findViewById(R.id.questions_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        qRecyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(qRecyclerView);
        questionRecyclerAdapter = new QuestionRecyclerAdapter(this);
        qRecyclerView.setAdapter(questionRecyclerAdapter);
    }

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

        Gson gson = new Gson();
        Intent intent = new Intent(this, ResultActivity.class);
        QuizResult qr = new QuizResult(getIntent().getStringExtra(MainFragment.CATEGORY_STR),
                getIntent().getStringExtra(MainFragment.DIFFICULTY),
                forAnswer,
                new Date(System.currentTimeMillis()),
                list,
                getIntent().getIntExtra(MainFragment.ID, 44)
        );
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
