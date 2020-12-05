package com.natlusrun.quizapp.ui.adapters;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.quizapp.data.model.QuestionModel;
import com.natlusrun.quizapp.databinding.AnswersItemBinding;

public class QuestionViewHolder extends RecyclerView.ViewHolder implements AnswerClickListener {


    public QuestionViewHolder(@NonNull AnswersItemBinding binding) {
        super(binding.getRoot());
    }

    public void onBind(QuestionModel model, AnswersItemBinding binding) {
        Button[] massiv = {binding.answerBtn1, binding.answerBtn2, binding.answerBtn3, binding.answerBtn4};

        if (model.getType().equals("multiple")) {
            binding.layoutBoolean.setVisibility(View.GONE);
            binding.layoutMultiple.setVisibility(View.VISIBLE);
            model.getIncorrectAnswers().add(model.getCorrectAnswer());
            for (int i = 0; i < massiv.length; i++) {
                massiv[i].setText(model.getIncorrectAnswers().get(i));
            }

            binding.answerBtn5.setText(model.getIncorrectAnswers().get(0));
            binding.answerBtn6.setText(model.getIncorrectAnswers().get(1));

        } else {
            binding.layoutMultiple.setVisibility(View.GONE);
            binding.layoutBoolean.setVisibility(View.VISIBLE);

            binding.answerBtn5.setText(model.getIncorrectAnswers().get(0));
            binding.answerBtn6.setText(model.getCorrectAnswer());
        }

        binding.questionTv.setText(model.getQuestion());


    }

    @Override
    public void onAnswerClick(boolean b, int adapterPosition) {

    }
}
