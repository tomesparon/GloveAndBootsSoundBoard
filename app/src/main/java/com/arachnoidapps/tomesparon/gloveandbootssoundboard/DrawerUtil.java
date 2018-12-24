package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import androidx.appcompat.widget.Toolbar;

public class DrawerUtil {
    public static void getDrawer(final Activity activity) {
        //if you want to update the items at a later time it is recommended to keep it in a variable

        PrimaryDrawerItem drawerItemHome = new PrimaryDrawerItem().withIdentifier(0)
                .withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home);

        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(1)
                .withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog);

        SecondaryDrawerItem drawerItemYoutube = new SecondaryDrawerItem().withIdentifier(2)
                .withName(R.string.drawer_item_youtube).withIcon(FontAwesome.Icon.faw_youtube);

        SecondaryDrawerItem drawerItemTwitter = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.drawer_item_twitter).withIcon(FontAwesome.Icon.faw_twitter);

        SecondaryDrawerItem drawerItemFacebook = new SecondaryDrawerItem().withIdentifier(4)
                .withName(R.string.drawer_item_facebook).withIcon(FontAwesome.Icon.faw_facebook);


        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(

                        drawerItemHome,
                        new DividerDrawerItem(),
                        drawerItemSettings,
                        drawerItemYoutube,
                        drawerItemTwitter,
                        drawerItemFacebook
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(activity, ((Nameable) drawerItem).getName().getText(activity), Toast.LENGTH_SHORT).show();

                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:

                                    Intent settingsIntent = new Intent(activity, SettingsActivity.class);
                                    view.getContext().startActivity(settingsIntent);
                                    return true;

                                case 2:
                                    Intent myWebLink2 = new Intent(android.content.Intent.ACTION_VIEW);
                                    myWebLink2.setData(Uri.parse("http://youtube.com/c/gloveandbootsofficial"));
                                    view.getContext().startActivity(myWebLink2);
                                    return true;
                                case 3:
                                    Intent myWebLink3 = new Intent(android.content.Intent.ACTION_VIEW);
                                    myWebLink3.setData(Uri.parse("http://twitter.com/gloveandboots"));
                                    view.getContext().startActivity(myWebLink3);
                                    return true;
                                case 4:
                                    Intent myWebLink4 = new Intent(android.content.Intent.ACTION_VIEW);
                                    myWebLink4.setData(Uri.parse("http://facebook.com/gloveandboots"));
                                    view.getContext().startActivity(myWebLink4);
                                    return true;
                                default:
                                    //should it be final???
                                    //result.closeDrawer();
                                    return true;
                            }


                        }
                        return false;
                    }
                }).build();
    }
}
