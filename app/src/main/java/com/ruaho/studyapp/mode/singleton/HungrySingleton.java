package com.ruaho.studyapp.mode.singleton;

/**
 * 饿汉式
 * 1）如果其他程序能够随意用 new 创建该类对象，那么就无法控制个数。因此，不让其他程序用 new 创建该类的对象。
 *
 * 2）既然不让其他程序 new 该类对象，那么该类在自己内部就要创建一个对象，否则该类就永远无法创建对象了。
 *
 * 3）该类将创建的对象对外(整个系统)提供，让其他程序获取并使用。
 *
 * 饿汉式：
 *
 * 一上来我就把对象给你 new 好了，你来了直接就可以拿去“吃”了
 * */
public class HungrySingleton {

    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return singleton;
    }

}
