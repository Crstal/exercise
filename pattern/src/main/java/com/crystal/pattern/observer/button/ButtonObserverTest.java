package com.crystal.pattern.observer.button;

import com.crystal.pattern.observer.core.Event;

public class ButtonObserverTest {

    public static void main(String[] args) throws Exception {
        Button button = new Button();

        button.addListener(ButtonEventType.CLICK, new ButtonCallback(), ButtonCallback.class.getMethod("onClick", Event.class));

        button.click();
    }
}
