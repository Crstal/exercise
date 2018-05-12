package com.crystal.pattern.factory.abstr;

import com.crystal.pattern.factory.Fridge;
import com.crystal.pattern.factory.HaierFridge;

public class FridgeFactory extends AbstractFridgeFactory {
    @Override
    public Fridge produceHaier() {
        Fridge fridge = new HaierFridge();
        fridge.setColor("blue");
        fridge.setSize("100");
        setDoor("海尔门"); //设置公共逻辑
        return fridge;
    }

    @Override
    public Fridge produceXimenzi() {
        Fridge fridge = new HaierFridge();
        fridge.setColor("red");
        fridge.setSize("100");
        setDoor("西门子门"); //设置公共逻辑
        return fridge;
    }
}
