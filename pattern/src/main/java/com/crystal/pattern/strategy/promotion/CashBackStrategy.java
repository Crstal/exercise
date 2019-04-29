package com.crystal.pattern.strategy.promotion;

public class CashBackStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("返现优惠，直接返现");
    }
}
