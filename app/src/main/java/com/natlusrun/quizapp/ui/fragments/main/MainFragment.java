package com.natlusrun.quizapp.ui.fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.databinding.MainFragmentBinding;
import com.natlusrun.quizapp.ui.activities.QuestionActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    protected TextView nameTv, increaseTv, decreaseTv, counterNumberTv;
    public static final String ID = "id";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_STR = "categoryStr";
    public static final String DIFFICULTY = "difficulty";
    public MainFragmentBinding binding;

    private Button startBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        binding.getRoot();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initViews(view);
        onCounterClick();
        startBtn.setOnClickListener(v -> {
Intent intent = new Intent(getContext(), QuestionActivity.class);
startActivity(intent);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.numberData.observe(getViewLifecycleOwner(), s -> counterNumberTv.setText(s));

    }

    private void initViews(View view) {
        nameTv = view.findViewById(R.id.name_tv);
        increaseTv = view.findViewById(R.id.increase_tv);
        decreaseTv = view.findViewById(R.id.decrease_tv);
        counterNumberTv = view.findViewById(R.id.counter_number_tv);
        startBtn = view.findViewById(R.id.start_btn);
    }

    private void onCounterClick() {
        increaseTv.setOnClickListener(v -> mViewModel.increaseNum());

        decreaseTv.setOnClickListener(v -> mViewModel.decreaseNum());
    }

}