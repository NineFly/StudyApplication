package com.ruaho.studyapp.mode.singleton;

/**
 * 懒汉式
 *
 * （要是有人问单例的延迟加载方式指的就是这种方式）
 *
 * 一开始我就不给你 new 对象，你来找我，我在给你创建一个对象
 * 懒汉式有一个缺点，就是在多线程中使用的时候，可能会创建多个实例对象，
 * 比如，线程1来调用 getInstance() 方法，判断了 s==null，然后线程1由于未知的原因阻塞了，
 * 线程2再来调用 getInstance() 方法，判断 s==null ，线程2就创建了一个对象，这时候线程1又运行了，
 * 那么线程1就会创建一个对象~这样就会造成多个对象~
 *
 * 饿汉式和懒汉式的区别：
 *
 * 1）饿汉式是空间换时间，懒汉式是空间换时间。
 *
 * 2）在多线程访问的时候，懒汉式可能会创建多个对象，而饿汉式不会。
 * */
public class LazySingleton {
    private static LazySingleton singleton = null;

    private LazySingleton(){

    }

    public static synchronized LazySingleton getInstance(){
        if (singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }

}
