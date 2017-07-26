package com.yorku.mstew.camera2videoimage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mstew on 2017-06-25.
 */

public class ViewPagerManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;


    public ViewPagerManager(Context context){
        this.context=context;
        pref=context.getSharedPreferences("first",0);
        editor=pref.edit();
    }
    public void setFirst(boolean isFirst){
        editor.putBoolean("check",isFirst);
        editor.commit();
    }
    public boolean Check(){
        return  pref.getBoolean("check",true);

    }
}
