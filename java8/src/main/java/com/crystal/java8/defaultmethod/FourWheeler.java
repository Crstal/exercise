package com.crystal.java8.defaultmethod;

public interface FourWheeler {
    // 默认方法
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}
