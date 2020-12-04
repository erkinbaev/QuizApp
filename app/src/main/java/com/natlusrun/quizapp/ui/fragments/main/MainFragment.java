package com.natlusrun.quizapp.ui.fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.model.CategoryModel;
import com.natlusrun.quizapp.databinding.MainFragmentBinding;
import com.natlusrun.quizapp.ui.activities.QuestionActivity;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private MainViewModel vm;
    protected TextView nameTv, increaseTv, decreaseTv, counterNumberTv;
    public static final String ID = "id";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_STR = "categoryStr";
    public static final String DIFFICULTY = "difficulty";
    public int id, category;
    public String difficulty;
    public MainFragmentBinding binding;

    private Button startBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(MainViewModel.class);

        initViews(view);
        onCounterClick();
        startBtn.setOnClickListener(v -> {
            id = Integer.parseInt(binding.questionAmountTv.getText().toString());
            category = vm.listCategory.getValue().get(binding.categorySp.getSelectedItemPosition()).getId();
            difficulty = binding.difficultyTv.getSelectedItem().toString().toLowerCase();

            Intent intent = new Intent(getContext(), QuestionActivity.class);
            intent.putExtra(ID, id);
            intent.putExtra(ID, category);
            intent.putExtra(ID, difficulty);
            Log.d("TAG", "onViewCreated: " + "id" + id + "category" + category + "difficulty" + difficulty);
            Log.d("TAG", "onViewCreated: " + "size of list" + vm.listCategory.getValue().size());

            startActivity(intent);
        });
        setSpinner();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm.numberData.observe(getViewLifecycleOwner(), s -> counterNumberTv.setText(s));

    }


    private void initViews(View view) {
        nameTv = view.findViewById(R.id.name_tv);
        increaseTv = view.findViewById(R.id.increase_tv);
        decreaseTv = view.findViewById(R.id.decrease_tv);
        counterNumberTv = view.findViewById(R.id.counter_number_tv);
        startBtn = view.findViewById(R.id.start_btn);
    }

    private void setSpinner() {
        vm.getCategories();
        vm.listCategory.observe(getViewLifecycleOwner(), categoryModels -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, vm.listStrCategory);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.categorySp.setAdapter(adapter);
        });
    }

    private void onCounterClick() {
        increaseTv.setOnClickListener(v -> vm.increaseNum());

        decreaseTv.setOnClickListener(v -> vm.decreaseNum());
    }


}