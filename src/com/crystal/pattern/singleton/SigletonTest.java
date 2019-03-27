package com.crystal.pattern.singleton;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

public class SigletonTest {

    @Test
    public void multiThreadTest() {
        final CountDownLatch latch = new CountDownLatch(100);

        for (int i=0; i<100; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        System.out.println(LazySingleton.getInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            latch.countDown();
        }
    }

    @Test
    public void invokeSingleton() throws Exception {
        ClassLoader.getSystemClassLoader().loadClass("com.crystal.pattern.singleton.LazyInnerClassSigleton");
        LazyInnerClassSigleton lazySingleton = LazyInnerClassSigleton.getInstance();
        System.out.println(lazySingleton);
        try {
            Constructor<LazyInnerClassSigleton> constructor = LazyInnerClassSigleton.class.getDeclaredConstructor();
            // 强制访问，强吻
            constructor.setAccessible(true);
            if (constructor != null) {
                lazySingleton = constructor.newInstance();
            }
            System.out.println(lazySingleton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void seriableSingleton() throws Exception {
        SeriableSingleton seriableSingleton = SeriableSingleton.getInstance();
        System.out.println(seriableSingleton);
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("SeriableSingleton.obj"));
            outputStream.writeObject(seriableSingleton);

            inputStream = new ObjectInputStream(new FileInputStream("SeriableSingleton.obj"));
            seriableSingleton = (SeriableSingleton) inputStream.readObject();
            System.out.println(seriableSingleton);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }
}
