package com.hongdu.gupao.chain;

import com.hongdu.gupao.chain.annotation.FixedPrivPrice;
import com.hongdu.gupao.chain.price.PriceStrategy;

/**
 * @ClassName SimpleStrategy1
 * @Description 简单价格的计算策略2
 * @Author dudu
 * @Date 2019/7/18 16:51
 * @Version 1.0
 */
@FixedPrivPrice(min = 500, max = Integer.MAX_VALUE)
public class SimpleStrategy2 implements PriceStrategy {

    @Override
    public double calcPrice(double price) {
        return price * 0.7;
    }
}
