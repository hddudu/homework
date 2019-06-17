package com.hongdu.gupao.strategy.activity;

public class CashBackStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("返现促销!");
    }
}
