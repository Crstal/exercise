package com.crystal.pattern.strategy.payport;

import com.crystal.pattern.strategy.MsgResult;

import java.math.BigDecimal;

public interface Payment {

    /**
     * 支付
     * @param orderId
     * @param amount
     * @return
     */
    MsgResult pay(String orderId, BigDecimal amount);
}
