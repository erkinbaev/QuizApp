package com.natlusrun.quizapp.ui.fragments.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.natlusrun.quizapp.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView nameTv, increaseTv, decreaseTv, counterNumberTv;
    int position = 0;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        onCounterClick();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.numberData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                counterNumberTv.setText(s);
            }
        });
//        ArrayList<String> names = new ArrayList<>();
//        names.add("Аъзамжон");
//        names.add("Азиз");
//        names.add("Орозбек");
//        names.add("Талгар");
//        names.add("Тилек");
//        names.add("Миранда");
//        names.add("Курманжан");
//        names.add("Нурсултан");


//        for (int i = 0; i < names.size(); i++) {
//            try {
//                mViewModel.getName(names.get(i));
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }

//        new Handler().postDelayed(new Runeable() {
//            @Override
//            public void run() {
//                if (position <= names.size() - 1) {
//
//                    position++;
//                }
//
//            }
//        }, 2000);
    }

    private void initViews(View view) {
        nameTv = view.findViewById(R.id.name_tv);
        increaseTv = view.findViewById(R.id.increase_tv);
        decreaseTv = view.findViewById(R.id.decrease_tv);
        counterNumberTv = view.findViewById(R.id.counter_number_tv);
    }

    private void onCounterClick() {
        increaseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.increaseNum();
            }
        });

        decreaseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.decreaseNum();
            }
        });
    }

}