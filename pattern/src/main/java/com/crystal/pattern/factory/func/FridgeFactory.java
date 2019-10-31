package com.crystal.pattern.factory.func;

import com.crystal.pattern.factory.Fridge;

/**
 * @author: Caoyue
 * @Description: 工厂方法模式 符合开闭原则，但是只针对同一类产品
 * @Date: 2018/05/12 15:56
 */
public interface FridgeFactory {

    public Fridge produceFridge();
}
