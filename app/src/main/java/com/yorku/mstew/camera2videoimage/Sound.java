package com.yorku.mstew.camera2videoimage;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by mstew on 2017-07-29.
 */

public class Sound {
    private static SoundPool soundPool;
    private static int hitSound;



    public Sound(Context context){
        //SoundPool(int maxStreams,int streamType, int srcQuality)
        soundPool=new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        hitSound=soundPool.load(context,R.raw.takepic,1);

    }
    public void playHitSound(){


        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);

    }

}
