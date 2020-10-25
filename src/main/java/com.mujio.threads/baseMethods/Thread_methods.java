package com.mujio.threads.baseMethods;

import com.mujio.threads.baseImplations.Thread_Runnable;
import com.mujio.threads.baseImplations.Thread_Thread;

/**
 * @Description: Thread_methods 线程常用方法，方法中会有很多异常抛出
 * @Author: GZY
 * @Date: 2020/6/7 0007
 */

public class Thread_methods {
    public static void main(String[] args) throws InterruptedException {
        /*Thread_Runnable thread_runnable = new Thread_Runnable();
        // 启动
        new Thread(thread_runnable).start();
        // 获取当前线程对象
        Thread thread = Thread.currentThread();
        // 获取线程优先级,线程优先级越高被CPU调度的概率越大，但是并不一定执行，也有小概率执行优先级低的线程
        int priority = thread.getPriority();
        System.out.println(priority);
        // 设置优先级
        thread.setPriority(10);
        priority = thread.getPriority();
        System.out.println(priority);
        // 判断线程是否存活,线程start()后处于alive状态，die或其他状态为false
        boolean alive = thread.isAlive();
        System.out.println(alive);*/
        // 强行执行
        A a = new A();
        B b = new B();
        a.start();
        // 不加join时，AB线程顺序随机，加了后main线程会进入A等待a.join()之前的线程执行完再执行下面的语句
        // a.join();
        // System.out.println("分水岭");
        // b.start();
        // 使当前执行的线程进入休眠状态，阻塞
        // Thread.sleep(100);
        // 礼让其他线程先执行（小概率自己继续执行）
        b.yield();
        // 中断线程
        // b.interrupt();
        // 判断是否守护线程,gc线程等属于守护线程；
/*        守护线程是运行在后台的一种特殊进程，它独立于控制终端，并且周期性地执行某种任务或着等待处理某些发生的事件。也就是在程序运行的时候在后台提供一种通用服务的线程，在没有用户线程客服务时会自动离开。
        生命周期：守护线程并不是程序中不可缺少的部分。当所有的非守护线程即用户线程结束，程序也就终止了，同时还会kill掉进程中的所有守护线程。
        守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。*/
        boolean daemon = a.isDaemon();
        System.out.println(daemon);
        a.setDaemon(true);
        daemon = a.isDaemon();
        System.out.println(daemon);
/*        //todo:以下方法要在同步方法或代码块中使用
        // 线程进入等待状态
        a.wait();
        // 唤醒当前线程
        b.notify();
        // 唤醒所有线程
        b.notifyAll();*/
    }
}

class A extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("A------");
        }
    }
}

class B extends Thread {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("B------");
        }
    }
}