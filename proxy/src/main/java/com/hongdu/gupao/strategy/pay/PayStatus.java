package com.hongdu.gupao.strategy.pay;


public class PayStatus {
    private Integer code;
    private Object data;
    private String msg;

    public PayStatus(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "PayStatus{" +
                "支付状态：【" + code +
                "】, data=" + data +
                ", 交易详情：【" + msg + '】' +
                '}';
    }
}
