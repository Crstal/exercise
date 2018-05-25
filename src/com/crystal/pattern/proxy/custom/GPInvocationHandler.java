package com.crystal.pattern.proxy.custom;

import java.lang.reflect.Method;

/**
 * @author: Caoyue
 * @Description: 代理类实现该接口  调用真实类
 * @Date: 2018/05/24 20:50  
 */
public interface GPInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
