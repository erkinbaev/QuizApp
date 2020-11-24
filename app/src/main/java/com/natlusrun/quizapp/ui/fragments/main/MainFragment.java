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
    private TextView nameTv;
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
        nameTv = view.findViewById(R.id.name_tv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.nameData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //nameTv.setText(s);
                Log.d(TAG, "onChanged: " + s);
            }
        });

        ArrayList<String> names = new ArrayList<>();
        names.add("Аъзамжон");
        names.add("Азиз");
        names.add("Орозбек");
        names.add("Талгар");
        names.add("Тилек");
        names.add("Миранда");
        names.add("Курманжан");
        names.add("Нурсултан");


//        for (int i = 0; i < names.size(); i++) {
//            try {
//                mViewModel.getName(names.get(i));
//                Thread.sleep(2000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }

//        new Handler().postDelayed(new Runnable() {
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

}