package com.ruaho.studyapp.mode.singleton;

/**
 * Android 必须会的设计模式
 * https://www.jianshu.com/p/35f76e87ac45
 *
 *
 *
 * Created by ruaho on 2017/8/8.
 * 典型的一个应用就是管理我们的Activity，下面这个可以作为一个工具类，代码也很简单
 */

public class Singleton {

    /**
     * 加锁机制
     * 被volatile修饰的变量能够保证每个线程能够获取该变量的最新值，从而避免出现数据脏读的现象。
     */
    private static volatile Singleton instance = null;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}


/**
 public class ActivityManager {

 private static volatile ActivityManager instance;
 private Stack<Activity> mActivityStack = new Stack<Activity>();

 private ActivityManager(){

 }

 public static ActivityManager getInstance(){
 if (instance == null) {
 synchronized (ActivityManager.class) {
 if (instance == null) {
 instance = new ActivityManager();
 }
 }
 return instance;
 }

 public void addActicity(Activity act){
 mActivityStack.push(act);
 }

 public void removeActivity(Activity act){
 mActivityStack.remove(act);
 }

 public void killMyProcess(){
 int nCount = mActivityStack.size();
 for (int i = nCount - 1; i >= 0; i--) {
 Activity activity = mActivityStack.get(i);
 activity.finish();
 }

 mActivityStack.clear();
 android.os.Process.killProcess(android.os.Process.myPid());
 }
 }
 * */