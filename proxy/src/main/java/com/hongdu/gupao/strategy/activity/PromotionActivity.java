package com.hongdu.gupao.strategy.activity;

/**
 * 类似 静态 静态代理 ： 持有接口引用 :
 * 间接的用execute方法替代了多个doPromotin()方法
 */
public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void execute() {
        this.promotionStrategy.doPromotion();
    }
}
