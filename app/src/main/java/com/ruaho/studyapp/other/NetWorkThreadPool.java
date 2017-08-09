package com.ruaho.studyapp.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/5/3.
 * 多线程线程池
 */
public class NetWorkThreadPool {

    public static final int MULT = 5;
    public static final int SINGLE = 1;
    private ExecutorService threadPool;
    //线程池的数量
    public static final int THREAD_POOL_SIZE = 5;

    public static NetWorkThreadPool getInstance() {
        return singletonHolder.mutiInstance;
    }

    public static NetWorkThreadPool getSingleThread() {
        return singletonHolder.singleInstance;
    }

    public void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    private NetWorkThreadPool(int type) {
        if (type == MULT)
            threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        else
            threadPool = Executors.newSingleThreadExecutor();
    }

    /**
     * singleTonHolder
     */
    private static class singletonHolder {
        private static final NetWorkThreadPool mutiInstance = new NetWorkThreadPool(MULT);
        private static final NetWorkThreadPool singleInstance = new NetWorkThreadPool(SINGLE);
    }

}
