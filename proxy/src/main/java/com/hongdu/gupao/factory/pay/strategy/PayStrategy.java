package com.hongdu.gupao.factory.pay.strategy;

/**
 * @ClassName PayStrategy
 * @Description 支付策略 获取具体的支付算法
 *      在支付过程中用到支付算法
 * @Author dudu
 * @Date 2020/2/24 17:01
 * @Version 1.0
 */
public class PayStrategy {

    public static final String ALI_PAY = "AliPay";
    public static final String Union_Pay = "UnionPay";
    public static final String WeChat_Pay = "WeChatPay";
    public static final String CrossBorder_Pay = "CrossBorderPay";

    public static final String DEFAULT_PAY = ALI_PAY;


}
