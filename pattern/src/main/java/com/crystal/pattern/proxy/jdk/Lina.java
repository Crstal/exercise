package com.crystal.pattern.proxy.jdk;

import com.crystal.pattern.proxy.Person;

/**
 * 目标对象需要实现某个接口
 */
public class Lina implements Person {
    @Override
    public String findLove() {
        System.out.println("高富帅");
        System.out.println("正直");
        return "完成";
    }
}
