package com.natlusrun.quizapp.ui.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.QuestionModel;
import com.natlusrun.quizapp.data.model.StaticQuestionModel;
import com.natlusrun.quizapp.databinding.AnswersItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {

    private List<QuestionModel> staticQuestions = new ArrayList<>();
    private AnswersItemBinding binding;
    private AnswerClickListener listener;

    public QuestionRecyclerAdapter(AnswerClickListener listener) {
        this.listener = listener;
    }

    public void setList(ArrayList<QuestionModel> list) {
        staticQuestions = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = AnswersItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Button[] massiv = {binding.answerBtn1, binding.answerBtn2, binding.answerBtn3, binding.answerBtn4, binding.answerBtn5, binding.answerBtn6};
        for (int i = 0; i < massiv.length; i++) {
            int number = i;
            massiv[i].setOnClickListener(v -> holder.onBind(staticQuestions.get(position), number));

        }
        holder.setText(staticQuestions.get(position));

    }

    @Override
    public int getItemCount() {
        return staticQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button[] massiv = {binding.answerBtn1, binding.answerBtn2, binding.answerBtn3, binding.answerBtn4};
        Button[] btn_boolean = {binding.answerBtn5, binding.answerBtn6};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(QuestionModel model, int positionBtn) {
            boolean knopka = false;
            if (model.isKnopka()) {
                if (model.getType().equals("multiple")) {
                    binding.layoutBoolean.setVisibility(View.GONE);
                    binding.layoutMultiple.setVisibility(View.VISIBLE);

                } else {
                    binding.layoutMultiple.setVisibility(View.GONE);
                    binding.layoutBoolean.setVisibility(View.VISIBLE);
                }

                staticQuestions.get(getAdapterPosition()).setBtnPosition(positionBtn);
                setBackground(positionBtn);

                if (massiv[positionBtn].getText().equals(model.getCorrectAnswer()))
                    knopka = true;

                model.setKnopka(false);
            }

            if (getAdapterPosition() == staticQuestions.size() - 1)
                listener.openActivity();
            else
                listener.onAnswerClick(knopka, getAdapterPosition());
        }

        public void setText(QuestionModel model) {
            binding.questionTv.setText(model.getQuestion());
            if (model.getType().equals("multiple")) {
                for (int i = 0; i < massiv.length; i++) {
                    massiv[i].setText(model.getIncorrectAnswers().get(i));
                }
            } else {
                for (int i = 0; i < btn_boolean.length; i++) {
                    btn_boolean[i].setText(model.getIncorrectAnswers().get(i));
                }
            }


        }

        public void setBackground(int position) {
            Button[] massiv = {binding.answerBtn1, binding.answerBtn2, binding.answerBtn3, binding.answerBtn4, binding.answerBtn5, binding.answerBtn6};
            if (massiv[position].getText().equals(staticQuestions.get(getAdapterPosition()).getCorrectAnswer())) {
                massiv[position].setBackgroundResource(R.drawable.green_bg);
            } else {
                massiv[position].setBackgroundResource(R.drawable.red_bg);
            }
            massiv[position].setTextColor(Color.WHITE);

        }
    }
}
