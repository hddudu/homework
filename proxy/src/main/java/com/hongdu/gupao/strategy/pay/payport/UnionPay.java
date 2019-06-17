package com.hongdu.gupao.strategy.pay.payport;

/**
 * @ClassName JDPay
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/13 16:57
 * @Version 1.0
 */
public class UnionPay extends AbstractPayment {

    @Override
    public String getPayTypeName() {
        return "银联支付";
    }

    @Override
    protected Double queryBalance(String uid) {
        return 1102.0;
    }
}
