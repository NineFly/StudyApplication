package com.ruaho.studyapp.goodcode.push;

import java.lang.reflect.Method;

import static com.ruaho.studyapp.goodcode.push.PushFactory.PushType.HuaWeiPush;
import static com.ruaho.studyapp.goodcode.push.PushFactory.PushType.MiPush;

/**
 * Created by ruaho on 2017/9/21.
 */

/**
 * 测试代码如下 接口只是抽象出来,但是具体的实现还是之类去做,调用还是子类的对象去调用
     PushInterface push = PushFactory.createPush();
     if (null != push) {
         Bean bean = new Bean();
         PushInterface.PushInfo info = push.getInfo();
         bean.set("deviceToken", info.token);
         bean.set("deviceTokenType", info.type);
     }
 */
public class PushFactory {

    enum PushType{
        MiPush,
        HuaWeiPush
    }

    public static PushInterface createPush() {
        switch (useWhichPush()) {
            case MiPush:
                return new MiPush();
            case HuaWeiPush:
                return new HuaWeiPush();
            default:
                return null;
        }
    }

    private static PushType useWhichPush() {
        if (PushFactory.isHuaWeiPinpai()) {
            String emui = PushFactory.getEMUI();
            if (emui.startsWith("EmotionUI_")) {
                String emui_version = emui.substring(emui.length() - 3, emui.length());
                if (Double.valueOf(emui_version) >= 3.0) {
                    return HuaWeiPush;
                }
            }
        }
        return MiPush;

    }

    public static String getEMUI() {
        Class<?> classType = null;
        String buildVersion = null;
        try {
            classType = Class.forName("android.os.SystemProperties");
            Method getMethod = classType.getDeclaredMethod("get", new Class<?>[]{String.class});
            buildVersion = (String) getMethod.invoke(classType, new Object[]{"ro.build.version.emui"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildVersion;
    }

    public static boolean isHuaWeiPinpai() {
        return getDeveicePinpai().equals("HUAWEI");
    }

    public static String getDeveicePinpai() {
        return android.os.Build.MANUFACTURER;
    }

    public static boolean isXisoMi() {
        return getDeveicePinpai().equals("Xiaomi");
    }
}
