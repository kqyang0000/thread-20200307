package com.kqyang.c18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 提供一个容器，容器内有两个方法 add size
 * 线程1添加10哥元素到容器中，线程2实现监控元素个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 1.使用volatile解决
 * 问题：while死循环，浪费cpu，切t2不是线程安全的
 */
public class MyContainer2 {
    volatile List<Object> list = new ArrayList<>(10);

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer2 c = new MyContainer2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 线程结束");
        }, "t2").start();
    }
}
