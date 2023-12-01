package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import MainFrameLayout.GraphFragment;
import MainFrameLayout.IndicatorFragment;
import MainFrameLayout.SettingFragment;
import MainFrameLayout.VpAdapter;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private VpAdapter vpAdapter;

    private FloatingActionButton indicatorBtn, settingBtn, graphBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        indicatorBtn = findViewById(R.id.fab1);
        graphBtn = findViewById(R.id.fab2);
        settingBtn = findViewById(R.id.fab3);

        indicatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new IndicatorFragment());

            }
        });

        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new GraphFragment());

            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new SettingFragment());

            }
        });




    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFramelayout,fragment);
        fragmentTransaction.commit();

    }
}