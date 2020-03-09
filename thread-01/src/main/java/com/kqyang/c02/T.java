package com.kqyang.c02;

public class T {
    private int count = 10;

    public void m() {
        //任何线程要执行下面的代码，必须要拿到o的锁
        //互斥锁
        //synchronized锁定的是对象，不是代码块
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 10; i++) {
            t.m();
        }
    }
}
