package com.crystal.pattern.strategy.promotion;

public class CouponStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("优惠卷促销，购买减去优惠费用");
    }
}
