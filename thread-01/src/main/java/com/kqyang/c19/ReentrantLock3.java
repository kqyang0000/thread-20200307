package com.kqyang.c19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantLock可以进行尝试锁定，当无法锁定，或在指定时间内无法锁定，线程可以决定是否继续进行等待
 */
public class ReentrantLock3 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续继续执行
     * 可以根据tryLock返回值来判断是否锁定
     * 也可以指定tryLock的时间，由于tryLock时会抛出异常，所以需要在finally中进行unLock
     */
    void m2() {
        /*boolean locked = this.lock.tryLock();
        System.out.println("m2..." + locked);
        if (locked) {
            lock.unlock();
        }*/

        boolean locked = false;
        try {
            locked = this.lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                this.lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock3 l = new ReentrantLock3();
        new Thread(l::m1, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(l::m2, "t2").start();
    }
}
