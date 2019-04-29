package com.crystal.pattern.strategy.payport;

public enum PayType {
    DEFAULT_PAY("1000", new AliPay()),
    ALI_PAY("1001", new AliPay()),
    JD_PAY("1002", new JDPay()),
    WECHAT_PAY("1003", new WeChatPay());

    PayType(String code, Payment payment) {
        this.code = code;
        this.payment = payment;
    }

    private String code;
    private Payment payment;

    public static Payment getPayment(String code) {
        PayType[] payTypes = PayType.values();
        for (PayType payType:  payTypes) {
            if (payType.code.equals(code)) {
                return payType.payment;
            }
        }
        return DEFAULT_PAY.payment;
    }
}
