package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

import android.os.Bundle;

//import android.preference.ListPreference;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.settings, rootKey);
        }
}
