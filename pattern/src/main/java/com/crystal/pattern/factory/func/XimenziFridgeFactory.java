package com.crystal.pattern.factory.func;

import com.crystal.pattern.factory.Fridge;
import com.crystal.pattern.factory.XimenziFridge;

/**
 * @author: Caoyue
 * @Description: 西门子工厂
 * @Date: 2018/05/12 16:37
 */
public class XimenziFridgeFactory implements FridgeFactory {
    @Override
    public Fridge produceFridge() {
        return new XimenziFridge();
    }
}
