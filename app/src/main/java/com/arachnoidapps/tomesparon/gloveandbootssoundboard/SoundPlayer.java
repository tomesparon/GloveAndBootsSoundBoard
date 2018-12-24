package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

/**
 * Created by Tom Esparon on 05/01/2018.
 */
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;


class SoundPlayer {

    private MediaPlayer mPlayer;
    private Context mContext;

    private static final String TAG = "SoundPlayer";
    private final static int MAX_VOLUME = 100;
    private int soundVolume = 50;



    SoundPlayer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    void playSound(Sound sound) {
        int resource = sound.getResourceId();
        //final float volume = (float) (1 - (Math.log(MAX_VOLUME - soundVolume) / Math.log(MAX_VOLUME)));
        //mPlayer.setVolume(volume, volume);
        //mPlayer.setVolume(1, 1);
        //mPlayer.setVolume(0.09f , 0.09f);
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - soundVolume) / Math.log(MAX_VOLUME)));
        if (mPlayer != null) {
            if (mPlayer.isPlaying())
                mPlayer.stop();
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

    void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
