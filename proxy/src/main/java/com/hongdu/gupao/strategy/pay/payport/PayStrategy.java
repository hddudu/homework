package com.hongdu.gupao.strategy.pay.payport;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PayStrategy
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/13 17:00
 * @Version 1.0
 */
public class PayStrategy {

   public static final String ALI_PAY = "AliPay";
   public static final String Jd_Pay = "JdPay";
   public static final String Union_Pay = "UnionPay";
   public static final String WeChat_Pay = "WeChatPay";
   public static final String Fast_Pay = "FastPay";
   public static final String DEFAULT_PAY = ALI_PAY;

   private static Map<String, AbstractPayment> paymentMap = new HashMap<>();

   static {
       paymentMap.put(ALI_PAY, new AliPay());
       paymentMap.put(Jd_Pay, new JDPay());
       paymentMap.put(Union_Pay, new UnionPay());
       paymentMap.put(WeChat_Pay, new WeChatPay());
       paymentMap.put(Fast_Pay, new FastPay());
   }

   //获取支付方式 支付策略算法
   public static AbstractPayment get(String key) {
       if(!paymentMap.containsKey(key)) {
           return paymentMap.get(DEFAULT_PAY);//默认使用阿里支付 支付宝
       }
       return paymentMap.get(key);
   }


}
