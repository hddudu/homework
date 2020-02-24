package com.hongdu.gupao.factory.pay.factorymethod;

/**
 * @ClassName MethodPayFactory
 * @Description 工厂方法模式
 * @Author dudu
 * @Date 2020/2/24 16:24
 * @Version 1.0
 */
public class MethodPayFactory {

    public IPay newAliPay() {
        return new AliPay();
    }

    public IPay newUnionPay() {
        return new UnionPay();
    }

    public IPay newWeChatPay() {
        return new WeChatPay();
    }

    public IPay newCrossBorderPay() {
        return new CrossBorderPay();
    }
}
