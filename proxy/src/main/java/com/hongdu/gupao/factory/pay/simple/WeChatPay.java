package com.hongdu.gupao.factory.pay.simple;

/**
 * @ClassName ZhiFuBaoPay
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/24 16:14
 * @Version 1.0
 */
public class WeChatPay implements IPay {
    @Override
    public void pay() {
        System.out.println("使用微信支付!");
    }
}
