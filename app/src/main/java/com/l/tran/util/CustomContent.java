package com.l.tran.util;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class CustomContent extends Application {

    private  static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5ac08346");
        context = getApplicationContext();
    }
    public static  Context getContext(){
        return context;
    }
}
