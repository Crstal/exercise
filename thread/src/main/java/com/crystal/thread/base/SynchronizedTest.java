package com.crystal.thread.base;

/**
 * @Author crystal
 * @Description 线程安全
 * @Date 2019-11-07
*/
public class SynchronizedTest {

    private static int count;

    public static void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    /*public static void safeInc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (SynchronizedTest.class) {
            count++;
        }
    }*/

    /*public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            new Thread(() -> SynchronizedTest.safeInc()).start();
        }
        Thread.sleep(3000);
        System.out.println(count);
    }*/
}
