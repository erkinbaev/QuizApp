package com.natlusrun.quizapp.ui.fragments.settings;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.natlusrun.quizapp.databinding.SettingsFragmentBinding;
import com.natlusrun.quizapp.ui.fragments.history.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private SettingsFragmentBinding binding;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clearHistory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel

    }

    private void clearHistory() {
        binding.clearHistoryListener.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                App.quizDataBase.quizDao().deleteAll();
                
            }
        });
    }

}