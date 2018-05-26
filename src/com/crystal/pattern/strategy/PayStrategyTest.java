package com.crystal.pattern.strategy;

import com.crystal.pattern.strategy.payport.PayType;

import java.math.BigDecimal;

public class PayStrategyTest {
    public static void main(String[] args) {
        Order order = new Order("1001", "2018052611230", new BigDecimal(300));
        System.out.println(order.pay(PayType.ALI_PAY));
    }
}
