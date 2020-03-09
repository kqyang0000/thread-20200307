package com.kqyang.c15;

import java.util.concurrent.TimeUnit;

/**
 * synchronized同步代码块中的语句越少越好
 */
public class T {
    int count = 0;

    synchronized void m1() {
        //to do something, need not to sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //待同步代码块
        count++;
        //to do something, need not to sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        //to do something, need not to sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用细粒度的锁，可以使线程争用时间变短，从而提升效率
        synchronized (this) {
            count++;
        }
        //to do something, need not to sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
