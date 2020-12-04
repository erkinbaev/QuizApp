package com.natlusrun.quizapp.ui.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.StaticQuestionModel;
import com.natlusrun.quizapp.databinding.AnswersItemBinding;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    TextView questionTv;
    Button answer_btn1, answer_btn2, answer_btn3, answer_btn4;


    public QuestionViewHolder(@NonNull AnswersItemBinding binding) {
        super(binding.getRoot());
        questionTv = itemView.findViewById(R.id.question_tv);
    }

    public void onBind(StaticQuestionModel staticQuestionModel, AnswersItemBinding binding) {
        if (staticQuestionModel.getType().equals("multiple")) {
            binding.layoutBoolean.setVisibility(View.GONE);
            binding.layoutMultiple.setVisibility(View.VISIBLE);
        } else {
            binding.layoutMultiple.setVisibility(View.GONE);
            binding.layoutBoolean.setVisibility(View.VISIBLE);
        }

        questionTv.setText(staticQuestionModel.getQuestion());
        binding.questionTv.setText(staticQuestionModel.getQuestion());


    }
}
