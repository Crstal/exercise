package com.crystal.pattern.singleton;

public enum  EnumSigleton {
    INSTANCE;

    private Object data;
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSigleton getInstance() {
        return INSTANCE;
    }
}
