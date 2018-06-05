package com.crystal.pattern.observer.button;

import com.crystal.pattern.observer.core.Event;

/**
 * @author: Caoyue
 * @Description: 鼠标事件触发回调
 * @Date: 2018/06/01 17:03
 */
public class ButtonCallback {

    public void onClick(Event e) {
        System.out.println("button click");
        System.out.println(e);
    }

    public void onOver(Event e) {
        System.out.println("mouse hover");
        System.out.println(e);
    }
}
