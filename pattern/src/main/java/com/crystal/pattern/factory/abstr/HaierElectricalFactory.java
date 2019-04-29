package com.crystal.pattern.factory.abstr;

import com.crystal.pattern.factory.Fridge;
import com.crystal.pattern.factory.HaierFridge;
import com.crystal.pattern.factory.HaierTV;
import com.crystal.pattern.factory.TV;

/**
* @Author: caoyue
* @Description: 海尔产品族
* @Date: 15:25 2019/3/19
**/
public class HaierElectricalFactory extends AbstractElectricalFactory {

    @Override
    public Fridge produceFridge() {
        Fridge fridge = new HaierFridge();
        fridge.setColor("red");
        fridge.setSize("100");
        return fridge;
    }

    @Override
    public TV produceTV() {
        TV tv = new HaierTV();
        tv.setSize(100);
        return tv;
    }
}
