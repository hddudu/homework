package com.hongdu.gupao.strategy.activity;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式工厂 ： 工厂 + 容器式单例(只初始化一次)
 */
public class PromotionStrategyFactory {

    //静态常量： COUPON CASHBACK ASSEMABLE （Default Empty）
    private static final String coupon = "COUPON";
    private static final String cashback = "CASHBACK";
    private static final String assemable = "ASSEMABLE";
    private static final String empty = "EMPTY";
    //容器: map
    private static Map<String, PromotionStrategy> strategyMap = new HashMap<String, PromotionStrategy>();
    //静态块 : static
    static {
        strategyMap.put(coupon, new CouponStrategy());
        strategyMap.put(cashback, new CashBackStrategy());
        strategyMap.put(assemable, new AssembleStrategy());
        strategyMap.put(empty, new EmptyStrategy());
    }

    //get值 : get(key)
    public static PromotionStrategy getInstance(String promotionKey) {
        return strategyMap.get(promotionKey);
    }
}
