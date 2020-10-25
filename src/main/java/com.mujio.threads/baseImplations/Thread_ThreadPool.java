package com.mujio.threads.baseImplations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: Thread_ThreadPool
 * @Author: GZY
 * @Date: 2020/6/7 0007
 */

public class Thread_ThreadPool implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        // 2. Executors框架，利用线程池执行
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        ExecutorService threadPool4 = Executors.newScheduledThreadPool(5);

       for (int i=0; i < 100; i++) {
           Runnable runnable = new Thread_ThreadPool();
           // 打印的线程名称全部为：pool-1-thread-1
           threadPool1.execute(runnable);
           // 打印的线程名称为：pool-1-thread-1 ~ pool-1-thread-10 顺序不定
           threadPool2.execute(runnable);
           // 打印的线程名称为：pool-1-thread-1 ~ pool-1-thread-100 顺序不定
           threadPool3.execute(runnable);
           // 打印的线程名称为：pool-1-thread-1 ~ pool-1-thread-5 顺序不定
           threadPool4.execute(runnable);
       }
        Thread.sleep(1000);
        threadPool1.shutdownNow();
        threadPool2.shutdownNow();
        threadPool3.shutdownNow();
        threadPool4.shutdownNow();
    }


}
