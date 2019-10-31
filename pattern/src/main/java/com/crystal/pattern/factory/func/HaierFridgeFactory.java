package com.crystal.pattern.factory.func;

import com.crystal.pattern.factory.Fridge;
import com.crystal.pattern.factory.HaierFridge;

/**
 * @author: Caoyue
 * @Description: 海尔工厂
 * @Date: 2018/05/12 16:37
 */
public class HaierFridgeFactory implements FridgeFactory {
    @Override
    public Fridge produceFridge() {
        return new HaierFridge();
    }
}
