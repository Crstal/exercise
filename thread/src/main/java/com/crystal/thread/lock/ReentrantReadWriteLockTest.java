package com.crystal.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author crystal
 * @Description 读写锁使用
 * @Date 2019-11-24
*/
public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();

    private static Map<String, Object> cacheMap = new HashMap<>(1,1);

    public static Object get(String key) {
        readLock.lock();
        try {
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static Object writer(String key, Object val) {
        writeLock.lock();
        try {
            return cacheMap.put(key, val);
        } finally {
            writeLock.unlock();
        }
    }
}