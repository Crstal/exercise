package com.crystal.pattern.decorator;

/**
 * @author: Caoyue
 * @Description: 原类
 * @Date: 2018/06/05 16:41
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
