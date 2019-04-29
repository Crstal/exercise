package com.crystal.pattern.strategy.promotion;

/**
* @Author: caoyue
* @Description: 执行优惠
* @Date: 21:17 2019/3/13
**/
public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void execute() {
        this.promotionStrategy.doPromotion();
    }
}
