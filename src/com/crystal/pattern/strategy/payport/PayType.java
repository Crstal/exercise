package com.crystal.pattern.strategy.payport;

public enum PayType {
    ALI_PAY("1001", new AliPay()),
    JD_PAY("1002", new JDPay()),
    WECHAT_PAY("1003", new AliPay());

    PayType(String code, Payment payment) {
        this.code = code;
        this.payment = payment;
    }

    private String code;
    private Payment payment;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
