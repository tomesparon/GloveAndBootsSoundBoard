package com.arachnoidapps.tomesparon.gloveandbootssoundboard;

/**
 * Created by Tom Esparon on 05/01/2018.
 */
public class Sound {

    private String mName;
    private int mResourceId;

    Sound(String name, int resourceId) {
        this.mName = name;
        this.mResourceId = resourceId;
    }

    int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        this.mResourceId = resourceId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}

