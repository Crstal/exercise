package com.crystal.pattern.factory.simple;

/**
 * @author: Caoyue
 * @Description: 简单工厂模式测试
 * 添加一种冰箱就需要修改工厂同时客户端需要修改生产参数  不符合开闭原则
 * @Date: 2018/05/12 15:51
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {

        Factory factory = new SimpleFactory();

        System.out.println(factory.produceFridge("西门子").getName());
    }
}
