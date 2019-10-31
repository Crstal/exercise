package com.crystal.pattern.factory.func;

import com.crystal.pattern.factory.Fridge;

/**
 * @author: Caoyue
 * @Description: 用户需要知道使用的哪个工厂，并且不支持产品族
 * @Date: 2018/05/12 16:53
 */
public class FuncFactoryTest {
    public static void main(String[] args) {
        FridgeFactory factory = new HaierFridgeFactory();
        Fridge fridge = factory.produceFridge();
        System.out.println(fridge.getName());
    }
}
