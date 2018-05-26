package com.crystal.pattern.strategy.payport;

import com.crystal.pattern.strategy.PayState;

import java.math.BigDecimal;

/**
 * @author: Caoyue
 * @Description: 阿里支付
 * @Date: 2018/05/26 14:52
 */
public class AliPay implements Payment {
    @Override
    public PayState pay(String orderId, BigDecimal amount) {
        System.out.println("欢迎使用支付宝");
        System.out.println("查询账户余额，开始扣款");
        return new PayState(200,amount,"支付成功");
    }
}
