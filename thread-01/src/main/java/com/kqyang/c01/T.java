package com.kqyang.c01;

public class T {
    private int count = 10;
    private Object o = new Object();

    public void m() {
        //任何线程要执行下面的代码，必须要拿到o的锁
        //互斥锁
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}
