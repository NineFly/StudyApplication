package com.ruaho.studyapp.goodcode;

/**
 * Created by ruaho on 2017/9/21.
 */

public class HuaWeiPush implements PushInterface {


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
