package com.crystal.pattern.strategy;

/**
 * @author: Caoyue
 * @Description: 支付状态
 * @Date: 2018/05/26 14:44
 */
public class PayState {
    private Integer code;
    private Object data;
    private String msg;

    public PayState(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}
