package com.crystal.thread.base;

/**
 * @Author crystal
 * @Description 栅栏
 * @Date 2019-11-18
*/
public class VolatileBarrierTest {

    private static int i=1;
    private static boolean finish = false;

    public void writer() {
        i=10;
        finish = true;
    }

    public void reader() {
        if (finish) {
            System.out.println(i);
        }
    }
}
