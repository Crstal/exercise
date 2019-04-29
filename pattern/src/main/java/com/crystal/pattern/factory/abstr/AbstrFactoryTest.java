package com.crystal.pattern.factory.abstr;

/**
 * @author: Caoyue
 * @Description: 用户只需调用即可
 * @Date: 2018/05/12 16:54
 */
public class AbstrFactoryTest {
    public static void main(String[] args) {
        FridgeFactory factory = new FridgeFactory();
        factory.produceHaier();
    }
}
