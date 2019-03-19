package com.crystal.pattern.factory.abstr;

import com.crystal.pattern.factory.Fridge;
import com.crystal.pattern.factory.TV;

/**
* @Author: caoyue
* @Description: 家电工厂  产品族
* @Date: 15:00 2019/3/19
**/
public abstract class AbstractElectricalFactory {

    /**
     * 生产冰箱
     * @return
     */
    public abstract Fridge produceFridge();

    /**
     * 生产电视
     * @return
     */
    public abstract TV produceTV();
}
