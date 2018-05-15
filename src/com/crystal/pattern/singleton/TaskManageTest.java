package com.crystal.pattern.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Caoyue
 * @Description: 测试单例模式
 * @Date: 2018/05/15 19:18
 */
public class TaskManageTest {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(100);

        for (int i=0; i<100; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        System.out.println(TaskManage.getInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            latch.countDown();
        }


    }
}
