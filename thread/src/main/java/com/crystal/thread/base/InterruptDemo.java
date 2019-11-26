package com.crystal.thread.base;

import org.junit.Test;

/**
 * @Author crystal
 * @Description 线程终端测试
 * @Date 2019-11-04
*/
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()) {// 默认 false，thread.interrupt()中断后为 true
                i++;
            }
            System.out.println("num is " + i);
        }, "interruptDemo");
        thread.start();
        Thread.sleep(1);
        thread.interrupt(); // 中断
    }

    @Test
    public void interruptedTest() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true) {// 默认 false，thread.interrupt()中断后为 true
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("before " + Thread.currentThread().isInterrupted());
                    Thread.interrupted();
                    System.out.println("after " + Thread.currentThread().isInterrupted());
                }
            }
        }, "interruptDemo");
        thread.start();
        Thread.sleep(1);
        thread.interrupt(); // 中断
    }

    @Test
    public void interruptExceptionTest() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (Thread.currentThread().isInterrupted()) {
                System.out.println("before " + Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after " + Thread.currentThread().isInterrupted());
            }
        }, "interruptDemo");
        thread.start();
        thread.interrupt(); // 中断
    }
}
