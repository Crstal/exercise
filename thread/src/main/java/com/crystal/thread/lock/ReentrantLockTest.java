package com.crystal.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static Lock lock = new ReentrantLock();

    private static int count = 0;

    public static void inc() {
        lock.lock();
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
            count ++;
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            new Thread(()->{ReentrantLockTest.inc();}).start();
        }
        Thread.sleep(3000L);
        System.out.println(count);
    }
}
