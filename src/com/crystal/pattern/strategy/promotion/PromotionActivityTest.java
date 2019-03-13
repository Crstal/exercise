package com.crystal.pattern.strategy.promotion;

import org.apache.tools.ant.util.StringUtils;

/**
* @Author: caoyue
* @Description: 选择优惠策略执行
* @Date: 21:19 2019/3/13
**/
public class PromotionActivityTest {

    public static void main(String[] args) {
        PromotionActivity promotionActivity = null;
        String promotion = "COUPON";

        if (promotion.equals("COUPON")) {
            promotionActivity = new PromotionActivity(new CouponStrategy());
        } else if (promotion.equals("CASHBACK")) {
            promotionActivity = new PromotionActivity(new CashBackStrategy());
        } // ...
        promotionActivity.execute();

        new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(PromotionStrategyFactory.PromotionKey.CASHBACK)).execute();
    }
}
