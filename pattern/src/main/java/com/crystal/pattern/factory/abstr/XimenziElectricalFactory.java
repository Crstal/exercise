package com.crystal.pattern.factory.abstr;

import com.crystal.pattern.factory.*;

public class XimenziElectricalFactory extends AbstractElectricalFactory {

    @Override
    public Fridge produceFridge() {
        Fridge fridge = new XimenziFridge();
        fridge.setColor("red");
        fridge.setSize("100");
        return fridge;
    }

    @Override
    public TV produceTV() {
        TV tv = new XimenziTV();
        tv.setSize(100);
        return tv;
    }
}
