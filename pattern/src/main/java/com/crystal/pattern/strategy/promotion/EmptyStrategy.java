package com.crystal.pattern.strategy.promotion;

public class EmptyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("没有任何优惠，原价");
    }
}
