package com.kqyang.c19;

import java.util.concurrent.TimeUnit;

/**
 * ReentrantLock用于替代synchronized
 * 由于m1锁定的是this，所以只有在m1执行完毕之后m2才会执行
 */
public class ReentrantLock1 {
    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2() {
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        ReentrantLock1 r = new ReentrantLock1();
        new Thread(r::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r::m2).start();
    }
}
