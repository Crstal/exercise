package com.crystal.pattern.singleton;

/**
* @Author: caoyue
* @Description: 懒汉模式
* @Date: 17:57 2019/3/19
**/
public class LazySingleton {

    private static LazySingleton lazySingleton;

    private LazySingleton() {
        if (lazySingleton != null) {
            throw new RuntimeException("已经初始化单例对象");
        }
    }

    /**
     * 获取实例
     * @return
     */
    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

    /**
     * 防止反序列化破坏单例
     * @return
     */
    private Object readResolve(){
        System.out.println("read resolve");
        return lazySingleton;
    }
}
