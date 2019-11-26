package com.crystal.thread.base;

/**
 * @Author crystal
 * @Description 线程状态测试
 * @Date 2019-11-01
*/
public class ThreadStatusTest {

    public static void main(String[] args) {
        // WAITING，线程在 ThreadStatus 类锁上通过 wait 进行等待
        Thread waiting = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (ThreadStatusTest.class) {
                        try {
                            ThreadStatusTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "waiting");
        waiting.start();
        System.out.println(waiting.getState());

        Thread timeWaiting = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Time-waiting");
        timeWaiting.start();
        System.out.println(timeWaiting.getState());

        //线程在 BlockedDemo 加锁后，不会释放锁
        Thread blocked1 = new Thread(new BlockedDemo(), "blocked1");
        Thread blocked2 = new Thread(new BlockedDemo(), "blocked2");
        blocked1.start();
        blocked2.start();
        System.out.println(blocked1.getState()+ "---" +  blocked2.getState());


    }

}

class BlockedDemo implements Runnable {

    @Override
    public void run() {
        synchronized (BlockedDemo.class) {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
