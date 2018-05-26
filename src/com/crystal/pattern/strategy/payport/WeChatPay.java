package com.crystal.pattern.strategy.payport;

import com.crystal.pattern.strategy.PayState;

import java.math.BigDecimal;

/**
 * @author: Caoyue
 * @Description: 微信支付
 * @Date: 2018/05/26 15:01
 */
public class WeChatPay implements Payment {
    @Override
    public PayState pay(String orderId, BigDecimal amount) {
        System.out.println("欢迎使用微信支付");
        System.out.println("查询账户余额，开始扣款");
        return new PayState(200,amount,"支付成功");
    }
}
