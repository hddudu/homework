package com.hongdu.gupao.chain;

import com.hongdu.gupao.chain.annotation.FixedPrivPrice;
import com.hongdu.gupao.chain.price.PriceStrategy;

/**
 * @ClassName SimpleStrategy1
 * @Description 简单价格的计算策略1
 * @Author dudu
 * @Date 2019/7/18 16:51
 * @Version 1.0
 */
@FixedPrivPrice(min = 100, max = 500)
public class SimpleStrategy1 implements PriceStrategy {

    @Override
    public double calcPrice(double price) {
        return price * 0.8;
    }
}
