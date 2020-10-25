# mujio-concurrent-programming

#### Description
并发编程学习

#### Categories

1.基础概念

    1.1 线程实现
    1.2 常用方法
    1.3 同步与非同步
    1.4 异常与锁
    1.5 锁升级
    1.6 锁重入
    1.7 volatile
    1.8 原子类
    1.9 wait、nocity


​    
2. JUC并发工具

    2.1 cas原理
    2.2 可重入锁
    2.3 condition条件等待与通知
    2.4 latch
    2.5 cyclicBarrer线程栅栏
    2.6 信号量
    2.7 Semaphore与Lock
    2.8 ThreadLocal本地变量


3. 线程池

    3.1 ThreadPool与Executor
    3.2 ExecutorService
    3.3 Execution
    3.4 Callable带返回值的Runnable
    3.5 异步调用Future
    3.6 常用线程池：
        fixed、cached、single、schedule、
        worksteaking、forkjoin、
    3.7 ParallelStreamAPI
    
4. 同步容器

5. Disruptor

6. 顺序执行控制

7. Quasor类库

8. JDK13、14变化





------



#### synchronized用法

###### 修饰方法

```java
public synchronized void test() {
        System.out.println("修饰方法");
    }
```

synchronized修饰方法时，不会被继承，子类若想要实现同步需要添加synchronized关键字或者调用父类方法：

```java
class Parent {
    public synchronized void method() {
        System.out.println("这是父类");
    }
}
class Child1 extends Parent {
	// 子类需要显式添加synchronized关键字
    public synchronized void method() {
        System.out.println("这是子类1");
    }
}
class Child2 extends Parent {
	// 也可以调用父类的同步方法以实现同步
    public void method() {
        super.method();
    }
}
```

> 注意：
>
> 1. synchronized不能修饰接口方法和抽象方法
> 2. synchronized不能修饰构造方法
> 3. 可以修饰静态方法，表示锁定该类所有实例对此方法的调用



###### 修饰代码块

```java
 public void test() {
        synchronized (this) {
            System.out.println("test");
        }
    }
```



###### 修饰对象

```java
class Demo3 {
    private Integer a = 0;
//    private Integer b = 0;// 1
    private Integer b = 10;// 2
    public static void main(String[] args) {
        Demo3 test = new Demo3();
        test.A();
        test.B();
    }

    public void A() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A线程准备"); // 3
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
                System.out.println("B线程准备");// 4
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
```

输出结果：

```
A线程准备
A线程开始
a0
B线程准备
a1
a2
B线程开始
b0
b1
b2
```



> 1. synchronized修饰对象的时候需要注意NPE；
> 2. 同步唯一的成员变量时等同于synchronized(this)，即等同于同步所有实例；
> 3. 修饰基本类型的包装类时，应注意包装类型通常是有缓存的，例如Integer在-128~127使用的是同一个对象，使用1处代码时，a，b为同一个对象，锁住a时也锁住了b，程序执行到3和4处时，对象被锁住，A方法先获取锁执行，A执行结束B才开始从4处接着执行；使用2处代码时，a、b属于不同的对象，AB将交替打印。



###### 修饰类：

```java
class Demo4 {
    public void test(){
        synchronized (Demo4.class) {
            System.out.println("test");
        }
    }
}
```

修饰类时，类似与修饰静态方法，所有的实例将共用一把锁。





无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁