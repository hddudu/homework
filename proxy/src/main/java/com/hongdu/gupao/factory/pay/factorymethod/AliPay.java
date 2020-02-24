package com.hongdu.gupao.factory.pay.factorymethod;


/**
 * @ClassName ZhiFuBaoPay
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/24 16:14
 * @Version 1.0
 */
public class AliPay implements IPay {
    @Override
    public void pay() {
        System.out.println("使用支付宝支付!");
    }
}
