package com.hongdu.gupao.chain;

import com.hongdu.gupao.chain.price.PriceStrategy;

/**
 * @ClassName DefaultPriceStrategy
 * @Description 默认的价格优惠策略类，用于无可用策略时，将不对价格进行优惠
 * @Author dudu
 * @Date 2019/7/18 16:51
 * @Version 1.0
 */
public class DefaultPriceStrategy implements PriceStrategy {

    @Override
    public double calcPrice(double price) {
        return 0;
    }
}
