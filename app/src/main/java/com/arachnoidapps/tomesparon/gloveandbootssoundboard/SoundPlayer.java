package com.arachnoidapps.tomesparon.gloveandbootssoundboard;
/**
 * Created by Tom Esparon on 05/01/2018.
 */
import java.io.IOException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import androidx.preference.PreferenceManager;

class SoundPlayer {
    private MediaPlayer mPlayer;
    private Context mContext;
    private static final String TAG = "SoundPlayer";
    private final static int MAX_VOLUME = 100;


    SoundPlayer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    void playSound(Sound sound) {
        int resource = sound.getResourceId();

        // get the settings stored value for volume and use in the playSound method
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(mContext);
        int pVol = sharedPreferences.getInt("volumebar_preference",50);

        final float volume = (float) (1 - (Math.log(MAX_VOLUME - pVol) / Math.log(MAX_VOLUME)));
        if (mPlayer != null) {
            if (mPlayer.isPlaying())
                mPlayer.stop();
            // something something release unused threads
            //mPlayer.reset();
            //release();

            try {
                AssetFileDescriptor afd =
                        mContext.getResources().openRawResourceFd(resource);
                if (afd == null)
                    return;

                mPlayer.reset();
                mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mPlayer.setVolume(volume, volume);
                mPlayer.prepare();
            } catch (IOException | IllegalArgumentException | SecurityException e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            mPlayer = MediaPlayer.create(mContext, resource);
            mPlayer.setVolume(volume, volume);
        }
        mPlayer.start();
    }
    //doesn't seem to be used, but should release old instances of mPlayer
    void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
