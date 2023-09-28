package com.example.air_quality_monitoring_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VpAdapter extends FragmentStateAdapter {


    public VpAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CO2Fragment();
            case 1:
                return new NO2Fragment();
            case 2:
                return new AQIFragment();
            case 3:
                return new UVIFragment();
            default:
                return new CO2Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}