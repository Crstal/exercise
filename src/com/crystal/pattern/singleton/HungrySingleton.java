package com.crystal.pattern.singleton;

/**
* @Author: caoyue
* @Description: 饿汉模式
* @Date: 18:14 2019/3/19
**/
public class HungrySingleton {

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
        if (hungrySingleton != null) {
            throw new RuntimeException("已经初始化单例对象");
        }
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
