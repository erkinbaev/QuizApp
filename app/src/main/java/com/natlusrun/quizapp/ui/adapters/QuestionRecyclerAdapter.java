package com.natlusrun.quizapp.ui.adapters;

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
        binding.setViewHolder(holder);
        binding.setModel(staticQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return staticQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(QuestionModel model, int positionBtn) {
            boolean knopka = false;

            if (model.isKnopka()) {

                Button[] massiv = {binding.answerBtn1, binding.answerBtn2, binding.answerBtn3, binding.answerBtn4};

                if (model.getType().equals("multiple")) {
                    binding.layoutBoolean.setVisibility(View.GONE);
                    binding.layoutMultiple.setVisibility(View.VISIBLE);

                } else {
                    binding.layoutMultiple.setVisibility(View.GONE);
                    binding.layoutBoolean.setVisibility(View.VISIBLE);
                }


                if (massiv[positionBtn].getText().equals(model.getCorrectAnswer()))
                    knopka = true;

                model.setKnopka(false);
            }


            listener.onAnswerClick(knopka, getAdapterPosition());

        }
    }
}
