package com.mujio.threads.baseImplations;

/**
 * @Description: Thread_Runnable 实现Runnable接口，重写run方法
 * @Author: GZY
 * @Date: 2020/6/7 0007
 */

public class Thread_Runnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread_Runnable threadRunnable = new Thread_Runnable();
        // 启动
        new Thread(threadRunnable).start();
        new Thread(threadRunnable).start();
    }
}
