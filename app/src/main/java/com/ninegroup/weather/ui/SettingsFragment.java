package com.ninegroup.weather.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.ninegroup.weather.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
