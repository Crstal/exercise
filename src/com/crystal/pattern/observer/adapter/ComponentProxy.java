package com.crystal.pattern.observer.adapter;

import com.crystal.pattern.observer.core.Event;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: Caoyue
 * @Description: 使用动态代理
 * @Date: 2018/06/04 20:33
 */
public class ComponentProxy implements MethodInterceptor {

    private Object target;

    // 将观察者注册到被观察者的观察者列表中，当被观察者状态发生变化的时候通知观察者
    protected Map<String, Event> maps = new HashMap<>();

    public void addListener(ButtonEventType eventType, Object target, Method callback) {
        maps.put(eventType.getName(), new Event(target, callback));   // 注册观察者
    }

    // 触发事件
    public void trigger(String eventType) {
        boolean exist = false;
        for (String name : maps.keySet()) {
            if (name.equals(eventType)) {
                exist = true;
                break;
            }
        }
        if (!exist)
            return;
        trigger(maps.get(eventType).setTrigger(eventType));
    }

    private void trigger(Event event) {
        event.setSource(this).setTime(new Date());
        try {
            event.getCallback().invoke(event.getTarget(), event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
     * @param o：代理类对象
     * @param method：目标对象方法
     * @param objects：参数
     * @param methodProxy：代理对象方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object obj = methodProxy.invokeSuper(o, objects);   // 调用父类的目标方法，也就是目标方法
        trigger(method.getName());
        return obj;
    }
}
