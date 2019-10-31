package com.crystal.pattern.factory.simple;

import com.crystal.pattern.factory.Fridge;
import com.crystal.pattern.factory.HaierFridge;
import com.crystal.pattern.factory.XimenziFridge;

/**
 * @author: Caoyue
 * @Description: 生产冰箱 简单工厂模式
 * @Date: 2018/05/12 15:49
 */
public class SimpleFactory implements Factory {

    public Fridge produceFridge(String name) {
        if ("西门子".equals(name)) {
            return new XimenziFridge();
        } else if ("海尔".equals(name)) {
            return new HaierFridge();
        }
        return null;
    }
}
