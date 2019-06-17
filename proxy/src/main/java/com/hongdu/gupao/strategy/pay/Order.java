package com.hongdu.gupao.strategy.pay;

import com.hongdu.gupao.strategy.pay.payport.PayStrategy;
import com.hongdu.gupao.strategy.pay.payport.AbstractPayment;

public class Order {

    private String uid;
    private String orderId;
    private Double amount;

    public Order(String uid, String orderId, Double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public PayStatus pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public PayStatus pay(String payKey) {
        AbstractPayment payment = PayStrategy.get(payKey);
        System.out.println("欢迎使用" + payment.getPayTypeName());//
        System.out.println("本次交易金额为：" + amount + "，开始扣款...");
        return payment.pay(uid, amount);
    }
}
