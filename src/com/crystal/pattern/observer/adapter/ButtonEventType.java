package com.crystal.pattern.observer.adapter;

public enum ButtonEventType {

    CLICK("click"),
    HOVER("hover");

    ButtonEventType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ButtonEventType getEventByName(String name) {
        for (ButtonEventType e : ButtonEventType.values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
