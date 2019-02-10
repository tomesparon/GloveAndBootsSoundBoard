package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.mikepenz.materialdrawer.Drawer;


public class MainActivity extends AppCompatActivity {

    private SoundPlayer mSoundPlayer;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPlayer = new SoundPlayer(this);
        Sound[] soundArray = SoundStore.getSounds(this);

        GridView gridView = findViewById(R.id.gridView);

        final ArrayAdapter<Sound> adapter =
                new ArrayAdapter<>(this,R.layout.buttons, soundArray);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Sound sound = (Sound) parent.getItemAtPosition(position);
                mSoundPlayer.playSound(sound);
            }
        });

        DrawerUtil.getDrawer(this);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSoundPlayer.release();
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
