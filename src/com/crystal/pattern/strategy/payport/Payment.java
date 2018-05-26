package com.crystal.pattern.strategy.payport;

import com.crystal.pattern.strategy.PayState;

import java.math.BigDecimal;

public interface Payment {

    /**
     * 支付
     * @param orderId
     * @param amount
     * @return
     */
    public PayState pay(String orderId, BigDecimal amount);
}
