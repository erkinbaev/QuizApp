package com.natlusrun.quizapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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

        fragmentList = new ArrayList<>();
        fillFragments();

        //viewPager = findViewById(R.id.main_view_pager);
        nonSwipeViewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setItemIconTintList(null);

        nonSwipeViewPager.setAdapter(new ViewPagerAdapter(fragmentList, getSupportFragmentManager()));
        nonSwipeViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav) {
                    nonSwipeViewPager.setCurrentItem(0);
                } else if(item.getItemId() == R.id.history_nav){
                    nonSwipeViewPager.setCurrentItem(1);
                }else if(item.getItemId() == R.id.settings_nav){
                    nonSwipeViewPager.setCurrentItem(2);
                }
                return true;
            }
        });

        mainFragment = new MainFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.main_container, mainFragment)
//                .commit();
    }

    private void fillFragments() {
        fragmentList.add(new MainFragment());
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new SettingsFragment());

    }
}