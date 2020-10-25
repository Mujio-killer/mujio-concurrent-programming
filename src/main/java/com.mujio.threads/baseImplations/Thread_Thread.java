package com.mujio.threads.baseImplations;

/**
 * @Description: ThreadDemo 线程的基本实现之继承Thread类
 * @Author: GZY
 * @Date: 2020/6/4 0004
 */

public class Thread_Thread extends Thread{

    // 线程体，run()方法运行完，这个线程随即终止
    public void run() {
        // 打印当前正在执行的线程名称
        int i = 0;
        while (i < Integer.MAX_VALUE) {
            i++;
        }
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
    }


    public static void main(String[] args) throws InterruptedException {
        Thread_Thread threadT = new Thread_Thread();
        // 调用Thread的start()方法
        threadT.start();
//        Thread.sleep(1);
        Thread.sleep(1000);
        System.out.println("this is test");

    }
}
