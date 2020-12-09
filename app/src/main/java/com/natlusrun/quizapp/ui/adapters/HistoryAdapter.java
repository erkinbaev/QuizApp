package com.natlusrun.quizapp.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.QuizResult;
import com.natlusrun.quizapp.databinding.HistoryItemBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    ArrayList<QuizResult> list = new ArrayList<>();
    HistoryItemBinding binding;


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new HistoryViewHolder(binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        QuizResult m = list.get(position);
        binding.correctAnswersTv.setText("correctAnswers: " + m.getCorrectAnswerResult());
        binding.historyCategoryTv.setText("Category: " + m.getCategory());
        binding.historyDifficultyTv.setText("Difficulty: " + m.getDifficulty());
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm");
        String strDate = dateFormat.format(m.getCreatedAt());
        binding.historyDateTv.setText("Date: " + strDate);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void add(ArrayList<QuizResult> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
