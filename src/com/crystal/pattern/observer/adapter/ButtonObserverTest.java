package com.crystal.pattern.observer.adapter;

import com.crystal.pattern.observer.button.ButtonCallback;
import com.crystal.pattern.observer.core.Event;

import java.lang.reflect.Method;

public class ButtonObserverTest {

    public static void main(String[] args) throws Exception {
        Button button = new Button();
        ComponentProxy componentProxy = new ComponentProxy();
        Button proxyButton = (Button) componentProxy.getInstance(button);
        Method callback = ButtonCallback.class.getMethod("onClick", Event.class);
        componentProxy.addListener(ButtonEventType.CLICK, new ButtonCallback(), callback);
        proxyButton.click();
    }
}
