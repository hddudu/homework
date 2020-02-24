package com.hongdu.gupao.factory.pay.factorymethod;

/**
 * @ClassName Main
 * @Description  现在完成到了 简单方法的工厂模式的 支付业务场景
 *          如果我们现在又添加了 跨境支付呢 CrossBorder 国外支付
 *          ①如果不改动代码 只新增呢？
 *          ②如果改动代码，
 * @Author dudu
 * @Date 2020/2/24 16:27
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        MethodPayFactory methodPayFactory = new MethodPayFactory();
        IPay iPay1 = methodPayFactory.newAliPay();
        iPay1.pay();
        IPay iPay12 = methodPayFactory.newUnionPay();
        iPay12.pay();
        IPay iPay13 = methodPayFactory.newWeChatPay();
        iPay13.pay();

        IPay iPay14 = methodPayFactory.newCrossBorderPay();
        iPay14.pay();
    }
}
