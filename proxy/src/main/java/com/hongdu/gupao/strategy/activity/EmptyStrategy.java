package com.hongdu.gupao.strategy.activity;

public class EmptyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("无促销活动!");
    }
}
