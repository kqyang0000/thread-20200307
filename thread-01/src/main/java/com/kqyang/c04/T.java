package com.kqyang.c04;

public class T {
    private static int count = 10;

    public static void m() {
        //当synchronized修饰的方法为静态方法时，锁定的相当于当前对象的class对象
        synchronized (T.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            T.m();
        }
    }
}
