package com.crystal.java8.defaultmethod;

public interface Vehicle {
    // 默认方法
    default void print(){
        System.out.println("我是一辆车!");
    }

    // 静态方法
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}
