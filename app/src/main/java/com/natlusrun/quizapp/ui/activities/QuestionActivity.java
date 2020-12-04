package com.natlusrun.quizapp.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.StaticQuestionModel;
import com.natlusrun.quizapp.ui.adapters.QuestionRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView qRecyclerView;
    private QuestionRecyclerAdapter questionRecyclerAdapter;
    private static ArrayList<StaticQuestionModel> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        init();


        list.add(new StaticQuestionModel("тебе нравится гиктек?"               , "boolean" )); //2
        list.add(new StaticQuestionModel("что такое архитектура приложений?"   , "multiple" )); //4
        list.add(new StaticQuestionModel("самая красивая девушка гиктека?"     , "multiple" )); //4
        list.add(new StaticQuestionModel("что такое репозиторий?"              , "multiple" ));    //4
        list.add(new StaticQuestionModel("котлин или жава?"                    , "boolean" ));        //2
        list.add(new StaticQuestionModel("эльбрус миддл или сениор?"           , "boolean" ));  //2
        list.add(new StaticQuestionModel("год образования гиктека?"            , "multiple" ));        //4
        list.add(new StaticQuestionModel("мвп или мввм?"                       , "boolean" ));//2
        list.add(new StaticQuestionModel("чай или кофе?"                       , "boolean" ));//2
        list.add(new StaticQuestionModel("айдар байке или нургазы байке?"      , "boolean" ));//2


        questionRecyclerAdapter.setList(list);


    }

    private void init() {
        qRecyclerView = findViewById(R.id.questions_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        qRecyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(qRecyclerView);
        questionRecyclerAdapter = new QuestionRecyclerAdapter();
        qRecyclerView.setAdapter(questionRecyclerAdapter);
    }

}
