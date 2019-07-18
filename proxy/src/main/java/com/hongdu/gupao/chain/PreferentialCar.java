package com.hongdu.gupao.chain;

/**
 * @ClassName PreferentialCar
 * @Description 满100 减10的优惠策略
 * @Author dudu
 * @Date 2019/7/18 16:59
 * @Version 1.0
 */
public class PreferentialCar extends AbstractPriceChain {

    @Override
    public double calcPrice(double price) {
        // 这是一张满100百减10的优惠卡
        if(price > 100) {
            price -= 10;
        }
        return price;
    }
}
