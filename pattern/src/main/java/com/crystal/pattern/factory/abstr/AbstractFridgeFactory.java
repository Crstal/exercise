package com.crystal.pattern.factory.abstr;

import com.crystal.pattern.factory.Fridge;

/**
 * @author: Caoyue
 * @Description: 抽象工厂模式
 * @Date: 2018/05/12 16:38
 */
public abstract class AbstractFridgeFactory {
    private String door; // 冰箱门

    /**
     * 可能会有一些公共的属性、方法
     * @param door
     */
    public void setDoor(String door) {
        this.door = door;
    }

    public abstract Fridge produceHaier();

    public abstract Fridge produceXimenzi();
}
