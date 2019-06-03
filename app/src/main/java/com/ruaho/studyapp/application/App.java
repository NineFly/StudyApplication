package com.ruaho.studyapp.application;

import android.app.Application;
import android.content.Context;

import com.ruaho.studyapp.db.GreenDaoManager;
//import com.tencent.bugly.crashreport.CrashReport;

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
//        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
//        CrashReport.initCrashReport(getApplicationContext(), "18ebe0368f", true);//建议在测试阶段建议设置成true，发布时设置为false。

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static Context getAppContext(){
        return mContext;
    }
}
