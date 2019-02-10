package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        //setContentView(R.layout.activity_settings);
        //DrawerUtil.getDrawer(this);

    }

}