package com.hongdu.gupao.strategy.activity;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class PromotionTest {

    public static void main(String[] args) {
//        String promotionKey = "COUPON";
//        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getInstance(promotionKey);
//        promotionStrategy.doPromotion();

        //活动不变
        String promotionKey = "COUPON";
//        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getInstance(promotionKey);
        PromotionStrategy promotionStrategy = PromotionStrategyInterfaceFactory.getInstance(promotionKey);
        PromotionActivity promotionActivity = new PromotionActivity(promotionStrategy);
        promotionActivity.execute();
    }

//    public static void main(String[] args) {
//        //618
//        PromotionActivity activity618 = new PromotionActivity(new CashBackStrategy());
//        activity618.execute();
//        //1111
//        PromotionActivity activity1111 = new PromotionActivity(new AssembleStrategy());
//        activity1111.execute();
//        //待优化：每次都要new一个活动方案； 太麻烦
//
//        //使用策略进行优化
//        PromotionActivity promotionActivity = null;
//
//        String promotionKey = "COUPON";
//        if(StringUtils.equals(promotionKey, "COUPON")) {
//            promotionActivity = new PromotionActivity(new CouponStrategy());
//        } else if(StringUtils.equals(promotionKey, "ASSEMABLE")) {
//            promotionActivity = new PromotionActivity(new AssembleStrategy());
//        } else if(StringUtils.equals(promotionKey, "CASHBACK")) {
//            promotionActivity = new PromotionActivity(new CashBackStrategy());
//        } else {
//            promotionActivity = new PromotionActivity(new EmptyStrategy());
//        }
//        promotionActivity.execute();
//
//        // if else 太多 还是不好 : 还有个点： 直接new参数， 是不是每次调用的时候都回new一个对象呢? 会被回收吗？
//    }
}
