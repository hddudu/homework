package com.hongdu.gupao.strategy.activity;

public class AssembleStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("拼团优惠!");
    }
}
