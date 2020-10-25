package com.mujio.threads.baseImplations;

import java.util.concurrent.*;

/**
 * @Description: Thread_Runnable 实现Callable接口，返回指定类型参数
 * @Author: GZY
 * @Date: 2020/6/7 0007
 */

public class Thread_Callable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("------------start------------");
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws Exception {
        // 利用FutureTask来实现线程创建
        FutureTask task = new FutureTask(new Thread_Callable());
        new Thread(task).start();
        System.out.println(task.get());
    }
}
