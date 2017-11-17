package com.ruaho.studyapp.goodcode.push;

/**
 * Created by ruaho on 2017/9/21.
 */

public class MiPush implements PushInterface {

    // 注册push服务，注册成功后会向DemoMessageReceiver发送广播
    @Override
    public void registerPush() {

    }


    @Override
    public void initPushArgument() {

    }

    @Override
    public void clear() {

    }

    @Override
    public PushInfo getInfo() {
        PushInfo pushInfo = new PushInfo();
        pushInfo.type = "type";
        pushInfo.token = "token";
        return pushInfo;
    }
}
