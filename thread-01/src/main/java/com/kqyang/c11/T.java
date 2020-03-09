package com.kqyang.c11;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在过个线程间可见
 * A B线程都用到一个变量，java默认使A线程中保留一份copy，这样如果B线程修改了变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的值，也就是强制让所有线程到堆内存中中读取变量的值
 * 变量更改后，进行缓存变量过期通知
 */
public class T {
    volatile boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
