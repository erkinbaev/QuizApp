package com.natlusrun.quizapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.ui.adapters.ViewPagerAdapter;
import com.natlusrun.quizapp.ui.fragments.history.HistoryFragment;
import com.natlusrun.quizapp.ui.fragments.main.MainFragment;
import com.natlusrun.quizapp.ui.fragments.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private MainFragment mainFragment;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
        fillFragments();

        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setItemIconTintList(null);

        viewPager.setAdapter(new ViewPagerAdapter(fragmentList, getSupportFragmentManager()));
        mainFragment = new MainFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.main_container, mainFragment)
//                .commit();
    }

    private void fillFragments(){
        fragmentList.add(new MainFragment());
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new SettingsFragment());

    }
}