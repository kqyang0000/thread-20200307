package com.kqyang.c16;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象，对象的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，即锁定的对象发生改变，
 * 应该避免将锁定对象的引用变成另外一个对象
 */
public class T {
    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(t::m, "t2");
        //如果注掉了这句话，则t2永远得不到执行的机会
        t.o = new Object();
        thread.start();
    }
}
