package com.mujio.threads.concurrentDemos;

import java.security.PublicKey;

/**
 * @Description: Demo_synchronized synchronized基本原理与使用方法
 * @Author: GZY
 * @Date: 2020/6/7 0007
 */

public class Demo_synchronized {
    public synchronized void test() {
        System.out.println("修饰方法");
    }
}
class Parent {
    public synchronized void method() {
        System.out.println("这是父类");
    }
}
class Child1 extends Parent {
    public synchronized void method() {
        System.out.println("这是子类1");
    }
}
class Child2 extends Parent {
    public void method() {
        super.method();
    }
}

class Demo1 {
    public void test() {
        synchronized (this) {
            System.out.println("test");
        }
    }
}
class Demo2 {
    private Integer num = 1;

    public void test() {
        synchronized (num) {
            System.out.println("test");
        }
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        System.out.println(a==b);
        System.out.println(a==c);

    }
}

class Demo3 {
    private Integer a = 0;
//    private Integer b = 0;
    private Integer b = 10;
    public static void main(String[] args) {
        Demo3 test = new Demo3();
        test.A();
        test.B();
    }

    public void A() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A线程准备");
                synchronized (a) {
                    System.out.println("A线程开始");
                    for (int i = 0; i < 3; i++) {
                        System.out.println("a" + i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void B() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B线程准备");
                synchronized (b) {
                    System.out.println("B线程开始");
                    for (int i = 0; i < 3; i++) {
                        System.out.println("b" + i);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}

class Demo4 {
    public void test(){
        synchronized (Demo4.class) {
            System.out.println("test");
        }
    }
}