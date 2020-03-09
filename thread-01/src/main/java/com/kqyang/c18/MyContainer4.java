package com.kqyang.c18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 提供一个容器，容器内有两个方法 add size
 * 线程1添加10哥元素到容器中，线程2实现监控元素个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，notify不会释放锁（使用时，必须保证当前对象是锁定的）
 * 需要注意的是，运用这种方法，必须要t2先执行，也就是首先让t2监听到才可以
 * <p>
 * 注意下面程序的输出结果，并不是等到size=5是t2退出，而是在t1结束时t2才接受通知而退出
 * <p>
 * notify之后必须释放锁，t2退出后，也必须notify，通知t1继续执行
 */
public class MyContainer4 {
    volatile List<Object> list = new ArrayList<>(10);

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer4 c = new MyContainer4();
        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                lock.notify();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
