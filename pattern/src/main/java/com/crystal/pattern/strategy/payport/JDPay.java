package com.crystal.pattern.strategy.payport;

import com.crystal.pattern.strategy.MsgResult;

import java.math.BigDecimal;

/**
 * @author: Caoyue
 * @Description: 京东支付
 * @Date: 2018/05/26 14:52
 */
public class JDPay implements Payment {
    @Override
    public MsgResult pay(String orderId, BigDecimal amount) {
        System.out.println("欢迎使用京东支付");
        if (amount.compareTo(new BigDecimal(500)) >= 0) {
            System.out.println("开始扣款");
        } else {
            System.out.println("余额不足，支付失败");
            return new MsgResult(500, amount, "余额不足， 支付失败");
        }
        return new MsgResult(200,amount,"支付成功");
    }
}
