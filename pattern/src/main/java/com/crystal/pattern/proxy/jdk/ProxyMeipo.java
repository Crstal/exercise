package com.crystal.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Caoyue
 * @Description: 媒婆（需要实现InvocationHandler）目标类的接口的实现类作为代理类
 * @Date: 2018/05/22 19:26
 */
public class ProxyMeipo implements InvocationHandler {
    // 被代理对象
    private Object target;

    /**
     * 获取代理对象
     * @param target
     * @return
     * @throws Exception
     */
    public Object getInstance(Object target) throws Exception {
        this.target = target; // 将被代理对象保存
        Class<?> clazz = target.getClass();
        // 创建代理对象
        Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return proxy;
    }

    /**
     * 利用反射调用真实对象的方法
     * @param proxy：代理对象
     * @param method：被代理对象的某个方法
     * @param args：参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("find")) {
            System.out.println("开始物色");
            Object obj = method.invoke(target, args);
            System.out.println("相亲");
            return obj;
        }
        return null;
    }
}
