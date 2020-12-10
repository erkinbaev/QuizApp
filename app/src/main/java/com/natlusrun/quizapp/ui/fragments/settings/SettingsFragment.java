package com.natlusrun.quizapp.ui.fragments.settings;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.PreferenceUtils;
import com.natlusrun.quizapp.data.model.QuizResult;
import com.natlusrun.quizapp.data.model.Theme;
import com.natlusrun.quizapp.databinding.HistoryFragmentBinding;
import com.natlusrun.quizapp.databinding.SettingsFragmentBinding;
import com.natlusrun.quizapp.ui.activities.SplashScreenActivity;
import com.natlusrun.quizapp.ui.adapters.OnClickListener;
import com.natlusrun.quizapp.ui.adapters.ThemeAdapter;
import com.natlusrun.quizapp.ui.fragments.history.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsFragment extends Fragment implements OnClickListener {

    private SettingsViewModel mViewModel;
    private SettingsFragmentBinding binding;
    private ThemeAdapter themeAdapter;
    private RecyclerView recyclerView;
    public final static String THEME = "theme";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SettingsFragmentBinding.inflate(inflater);
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

        init();

    }

    private void clearHistory() {
        binding.clearHistoryListener.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                App.quizDataBase.quizDao().deleteAll();

            }
        });
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.themesRv.setLayoutManager(manager);
        themeAdapter = new ThemeAdapter(this, getContext());
        binding.themesRv.setAdapter(themeAdapter);
        themeAdapter.add(new Theme("Light"));
        themeAdapter.add(new Theme("Dark"));
        themeAdapter.add(new Theme("Silver"));
        themeAdapter.add(new Theme("Gold"));
        themeAdapter.add(new Theme("Native"));
    }

    @Override
    public void openFragment(int position) {
        SharedPreferences sp = App.sp;
        if (sp.getInt(THEME, 22) != position) {
            sp.edit().putInt(THEME, position).apply();
            startActivity(new Intent(getContext(), SplashScreenActivity.class));
            getActivity().finish();
        } else {
            Toast.makeText(getContext(), "Вы уже выбрали эту тему! " + position, Toast.LENGTH_SHORT).show();
        }
    }
}