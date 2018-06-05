package com.crystal.pattern.observer.mouse;

import com.crystal.pattern.observer.core.EventListener;

/**
 * @author: Caoyue
 * @Description: 鼠标类（被观察者），需要将观察者注册到自己的观察者列表中，当自身状态发生变化的时候通知观察者
 * @Date: 2018/06/01 17:00
 */
public class Button extends EventListener {

    public void click() {
        System.out.println("点击按钮");
        this.trigger(ButtonEventType.CLICK);
    }

    public void hover() {
        System.out.println("鼠标悬停在鼠标上");
        this.trigger(ButtonEventType.HOVER);
    }
}
