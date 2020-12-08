package com.natlusrun.quizapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.ui.adapters.NonSwipeViewPager;
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

    private NonSwipeViewPager nonSwipeViewPager;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillFragments();
        initViews();
        setViewPager();
        setBottomNavigationView();

//        mainFragment = new MainFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.main_container, mainFragment)
//                .commit();
    }

    private void fillFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment());
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new SettingsFragment());

    }

    private void initViews() {
        nonSwipeViewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
    }

    private void setBottomNavigationView() {
        getWindow().setNavigationBarColor(Color.WHITE);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav) {
                    nonSwipeViewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.history_nav) {
                    nonSwipeViewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.settings_nav) {
                    nonSwipeViewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }

    private void setViewPager() {
        nonSwipeViewPager.setAdapter(new ViewPagerAdapter(fragmentList, getSupportFragmentManager()));
        //nonSwipeViewPager.setOffscreenPageLimit(3);
        nonSwipeViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });


    }
}