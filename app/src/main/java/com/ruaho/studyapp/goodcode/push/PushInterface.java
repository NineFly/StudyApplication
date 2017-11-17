package com.ruaho.studyapp.goodcode.push;

/**
 * Created by ruaho on 2017/9/21.
 */
public interface PushInterface {
    /**
     * 注册推送
     */
    void registerPush();

    /**
     * 推送开始前要设置的一些参数
     */
    void initPushArgument();

    /**
     * 断开推送连接需要做的一些事情
     * 比如清理别名
     */
    void clear();

    /**
     * 推送要传给后台的一些参数
     */
    PushInfo getInfo();

    class PushInfo {
        public String token;
        public String type;
    }


}
