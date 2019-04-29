package com.crystal.pattern.observer.core;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Caoyue
 * @Description: 用于注册观察者
 * @Date: 2018/06/01 17:57
 */
public class EventListener {
    // 将观察者注册到被观察者的观察者列表中，当被观察者状态发生变化的时候通知观察者
    protected Map<Enum, Event> maps = new HashMap<>();

    public void addListener(Enum eventType, Object target, Method callback) {
        maps.put(eventType, new Event(target, callback));   // 注册观察者
    }

    // 触发事件
    public void trigger(Enum eventType) {
        if (!maps.containsKey(eventType))
            return;
        trigger(maps.get(eventType).setTrigger(eventType.toString()));
    }

    private void trigger(Event event) {
        event.setSource(this).setTime(new Date());
        try {
            event.getCallback().invoke(event.getTarget(), event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
