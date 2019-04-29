package com.crystal.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
* @Author: caoyue
* @Description:
* @Date: 21:27 2019/3/13
**/
public class PromotionStrategyFactory {

    private static final Map<PromotionKey, PromotionStrategy> PROMOTIN_STRATEGY_MAP = new HashMap<>();

    {
        PROMOTIN_STRATEGY_MAP.put(PromotionKey.EMPTY, new EmptyStrategy());
        PROMOTIN_STRATEGY_MAP.put(PromotionKey.CASHBACK, new CashBackStrategy());
        PROMOTIN_STRATEGY_MAP.put(PromotionKey.COUPON, new CouponStrategy());
    }

    private PromotionStrategyFactory() {}

    public static PromotionStrategy getPromotionStrategy(PromotionKey promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTIN_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy == null ? new EmptyStrategy() : promotionStrategy;
    }

    public enum PromotionKey {
        COUPON,
        CASHBACK,
        EMPTY;
    }
}
