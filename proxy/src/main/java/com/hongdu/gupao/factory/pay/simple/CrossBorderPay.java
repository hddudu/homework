package com.hongdu.gupao.factory.pay.simple;

/**
 * @ClassName CrossBorderPay
 * @Description 跨境支付
 * @Author dudu
 * @Date 2020/2/24 16:30
 * @Version 1.0
 */
public class CrossBorderPay implements IPay {
    @Override
    public void pay() {
        System.out.println("使用跨境支付!");
    }
}
