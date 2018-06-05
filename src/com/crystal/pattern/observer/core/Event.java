package com.crystal.pattern.observer.core;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author: Caoyue
 * @Description: 事件
 * @Date: 2018/06/01 17:11
 */
public class Event {
    private Object source;  // 事件源
    private Object target;  // 通知目标
    private Method callback;  // 回调
    private String trigger; // 触发
    private Date time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    public Object getSource() {
        return source;
    }

    public Event setSource(Object source) {
        this.source = source;
        return this;
    }

    public Object getTarget() {
        return target;
    }

    public Event setTarget(Object target) {
        this.target = target;
        return this;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public String getTrigger() {
        return trigger;
    }

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "\nEvent{" +
                "\nsource=" + source +
                ",\n target=" + target +
                ",\n callback=" + callback +
                ",\n trigger='" + trigger + '\'' +
                ",\n time=" + time +
                "\n}";
    }
}
