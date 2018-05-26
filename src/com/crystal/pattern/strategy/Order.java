package com.crystal.pattern.strategy;

import com.crystal.pattern.strategy.payport.PayType;
import com.crystal.pattern.strategy.payport.Payment;

import java.math.BigDecimal;

/**
 * @author: Caoyue
 * @Description: 策略模式 支付场景
 * @Date: 2018/05/26 14:40
 */
public class Order {
    private String uid;
    private String orderId;
    private BigDecimal amount;

    public Order(String uid, String orderId, BigDecimal amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    /**
     * 支付
     * @param payType
     * @return
     */
    public PayState pay(PayType payType) {
        return payType.getPayment().pay(this.orderId, this.amount);
    }
}
