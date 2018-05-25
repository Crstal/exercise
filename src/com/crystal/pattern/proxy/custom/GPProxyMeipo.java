package com.crystal.pattern.proxy.custom;

import java.lang.reflect.Method;

public class GPProxyMeipo implements GPInvocationHandler {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        if (target != null) {
            return GPProxy.newProxyInstance(new GPClassLoader(), target.getClass().getInterfaces(), this);
        }
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始物色");
        Object obj = method.invoke(target, args);
        System.out.println("相亲");
        return obj;
    }
}
