package com.hongdu.gupao.strategy.activity;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略模式工厂 ： 工厂 + 容器式单例(只初始化一次)
 */
public class PromotionStrategyInterfaceFactory {

    //私有化构造方法
    private PromotionStrategyInterfaceFactory(){}

    //静态常量： COUPON CASHBACK ASSEMABLE （Default Empty）
    private static final PromotionStrategy NON_PROMOTION = new EmptyStrategy();//单例

    //容器: map
    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();
    //静态块 : static : 静态内部接口（定义常量） + 初始化static完成 ==》 PROMOTION_STRATEGY_MAP的初始化
    //定义静态内部接口常量 ： 好处？
    private interface PromotionKey {
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String ASSEMABLE = "ASSEMABLE";
    }
    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON, new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK, new CashBackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.ASSEMABLE, new AssembleStrategy());
    }

    //get值 : get(key)
    public static PromotionStrategy getInstance(String promotionKey) {
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionKey);
        return promotionStrategy == null ? NON_PROMOTION : promotionStrategy;
    }
}
