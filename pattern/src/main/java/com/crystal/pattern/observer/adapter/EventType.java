package com.crystal.pattern.observer.adapter;

public enum EventType {

    CLICK("click"),
    HOVER("hover"),
    DBCLICK("dbclick");

    EventType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
