package com.natlusrun.quizapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.QuestionModel;
import com.natlusrun.quizapp.data.model.StaticQuestionModel;
import com.natlusrun.quizapp.databinding.AnswersItemBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    private List<StaticQuestionModel> staticQuestions = new ArrayList<>();
    private AnswersItemBinding binding;

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = AnswersItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.onBind(staticQuestions.get(position), binding);
    }

    public void setList(ArrayList<StaticQuestionModel> list) {
        staticQuestions = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return staticQuestions.size();
    }
}
