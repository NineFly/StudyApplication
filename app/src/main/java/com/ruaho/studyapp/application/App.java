package com.ruaho.studyapp.application;

import android.app.Application;
import android.content.Context;

import com.ruaho.studyapp.db.GreenDaoManager;

/**
 * Created by ruaho on 2017/8/9.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //GreenDao的初始化
        GreenDaoManager.getInstance();


    }

    public static Context getAppContext(){
        return mContext;
    }
}
