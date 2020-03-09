package com.kqyang.c06;

public class T {

    public static void main(String[] args) {
        T t = new T();
        /**
         * 同步方法和非同步方法是否可以同时调用
         */
        /*new Thread(() -> t.run1(), "t1").start();
        new Thread(() -> t.run2(), "t2").start();*/

        new Thread(t::run1, "t1").start();
        new Thread(t::run2, "t2").start();
    }

    public synchronized void run1() {
        System.out.println(Thread.currentThread().getName() + " run1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " run1 end");
    }

    public void run2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " run2");
    }


}
