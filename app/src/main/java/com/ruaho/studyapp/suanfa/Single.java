package com.ruaho.studyapp.suanfa;

/*单例模式*/
public class Single {

    /*饱汉写法*/
    private static Single instance ;
    public static Single getInstance() {
        if (instance == null){
            synchronized (Single.class){
                if (instance == null){
                    instance = new Single();
                }
            }
        }
        return instance;
    }

    /*懒汉写法*/
    private static Single ins2 = new Single();
    public static Single getIns2(){
        return ins2;
    }

}
