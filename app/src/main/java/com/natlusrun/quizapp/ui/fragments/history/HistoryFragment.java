package com.natlusrun.quizapp.ui.fragments.history;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.QuizResult;
import com.natlusrun.quizapp.databinding.HistoryFragmentBinding;
import com.natlusrun.quizapp.databinding.HistoryItemBinding;
import com.natlusrun.quizapp.ui.adapters.HistoryAdapter;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    HistoryFragmentBinding binding;

    HistoryAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HistoryFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        init();

    }

    private void init() {
        adapter = new HistoryAdapter();
        binding.historyRv.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.add((ArrayList<QuizResult>) App.quizDataBase.quizDao().getAll());
    }
}