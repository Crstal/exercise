package com.crystal.pattern.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @Author: caoyue
* @Description: 容器式单例
* @Date: 20:16 2019/3/27
**/
public class ContainerSingleton {

    private static final Map<String, Object> ioc = new ConcurrentHashMap<>();

    private ContainerSingleton() {}

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}
