package com.hongdu.gupao.factory.pay.simple;

/**
 * @ClassName UnionPay
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/24 16:15
 * @Version 1.0
 */
public class UnionPay implements IPay {
    @Override
    public void pay() {
        System.out.println("使用银联支付!");
    }
}
