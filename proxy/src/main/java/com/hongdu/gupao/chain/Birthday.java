package com.hongdu.gupao.chain;

/**
 * @ClassName Birthday
 * @Description 特殊价格优惠策略两个简单实现： 1L ： 生日优惠
 *                                              2: 满减卡 策略 : 满100 减10
 * @Author dudu
 * @Date 2019/7/18 16:57
 * @Version 1.0
 */
public class Birthday extends AbstractPriceChain {


    @Override
    public double calcPrice(double price) {
        //会员生日一律9折优惠
        return price * 0.9;
    }
}
