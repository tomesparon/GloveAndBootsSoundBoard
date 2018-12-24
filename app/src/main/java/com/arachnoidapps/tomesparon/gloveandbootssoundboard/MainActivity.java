package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;



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

        // Handle NavToolbar
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_youtube).withIcon(FontAwesome.Icon.faw_youtube),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_twitter).withIcon(FontAwesome.Icon.faw_twitter),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_facebook).withIcon(FontAwesome.Icon.faw_facebook)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();

                            switch (result.getCurrentSelectedPosition()) {
                                case 1:
                                    Log.e("Pos","Pos1");
                                    Intent settingsIntent = new Intent(view.getContext(), SettingsActivity.class );
                                    startActivity(settingsIntent);
                                    return true;

                                case 2:
                                    Intent myWebLink2 = new Intent(android.content.Intent.ACTION_VIEW);
                                    myWebLink2.setData(Uri.parse("http://youtube.com/c/gloveandbootsofficial"));
                                    startActivity(myWebLink2);
                                    return true;
                                case 3:
                                    Intent myWebLink3 = new Intent(android.content.Intent.ACTION_VIEW);
                                    myWebLink3.setData(Uri.parse("http://twitter.com/gloveandboots"));
                                    startActivity(myWebLink3);
                                    return true;
                                case 4:
                                    Intent myWebLink4 = new Intent(android.content.Intent.ACTION_VIEW);
                                    myWebLink4.setData(Uri.parse("http://facebook.com/gloveandboots"));
                                    startActivity(myWebLink4);
                                    return true;
                                default:
                                    result.closeDrawer();
                                    return true;
                            }
                        }

                        return false;
                    }
                }).build();

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



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
