package com.hongdu.gupao.factory.pay.simple;

/**
 * @ClassName PayFactory
 * @Description 简单支付工厂
 * @Author dudu
 * @Date 2020/2/24 16:16
 * @Version 1.0
 */
public class PayFactory {

    public IPay getPay(String payName) {
        if("ali".equalsIgnoreCase(payName)) {
            return new AliPay();
        } else if("wechat".equalsIgnoreCase(payName)) {
            return new WeChatPay();
        } else if("union".equalsIgnoreCase(payName)) {
            return new UnionPay();
        }
        //简单工厂中需要修改 if else 属于大忌
        else if("crossborder".equalsIgnoreCase(payName)) {
            return new CrossBorderPay();
        }
        return null;
    }
}
