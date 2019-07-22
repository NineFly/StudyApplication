package com.ruaho.studyapp.activity;

public class JNITest {

    // 动态导入 so 库
    static {
        System.loadLibrary("JNITest");
    }

    //创建一个 native 方法
    public native static String get();
}
