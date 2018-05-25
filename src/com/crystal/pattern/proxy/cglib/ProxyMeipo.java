package com.crystal.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: Caoyue
 * @Description: 需要实现MethodInterceptor（生成目标类的子类作为代理类）
 * @Date: 2018/05/22 20:04
 */
public class ProxyMeipo implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        // 工具类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass()); // 设置父类
        enhancer.setCallback(this); // 设置回调函数
        return enhancer.create();
    }

    /**
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始物色");
        Object obj = methodProxy.invokeSuper(o, objects); // 两种调用目标方法的方式
//        Object obj = method.invoke(target, objects);
        System.out.println("相亲");
        return obj;
    }
}
