package com.hongdu.gupao.strategy.pay.payport;

/**
 * @ClassName JDPay
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/13 16:57
 * @Version 1.0
 */
public class WeChatPay extends AbstractPayment {

    @Override
    public String getPayTypeName() {
        return "微信支付";
    }

    @Override
    protected Double queryBalance(String uid) {
        return 465.0;
    }
}
