package com.kqyang.c21;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如Hibernate中session就存在ThreadLocal中，避免使用synchronized
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        public String name = "zhangsan";
    }
}


