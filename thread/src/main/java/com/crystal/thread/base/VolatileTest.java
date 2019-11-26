package com.crystal.thread.base;

public class VolatileTest {

    /*private static volatile Object obj;

    public static void main(String[] args) {
        obj = new Object();
    }*/

    private volatile static boolean stop = false;

    public static void run() {
        Thread thread = new Thread(()->{
            int i = 0;
            while (!stop) {
                i ++;
            }
            System.out.println(i + "  stop");
        });
        thread.start();
        System.out.println("start");
    }

    public static void main(String[] args) throws InterruptedException {
        run();
        Thread.sleep(1000);
        stop = true;
        System.out.println(stop);
    }
}
