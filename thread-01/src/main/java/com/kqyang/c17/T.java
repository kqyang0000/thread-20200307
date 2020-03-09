package com.kqyang.c17;

/**
 * 不要以字符串常量做锁定对象，如果字符串常量相同，则锁定的是一个对象，
 * 容易引起诡异的死锁阻塞
 */
public class T {
    String a1 = "Hello";
    String a2 = "Hello";

    void m1() {
        synchronized (a1) {
        }
    }

    void m2() {
        synchronized (a2) {
        }
    }
}
