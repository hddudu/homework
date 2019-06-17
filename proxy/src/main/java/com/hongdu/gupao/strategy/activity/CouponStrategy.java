package com.hongdu.gupao.strategy.activity;

public class CouponStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("领取优惠券抵扣!");
    }
}
