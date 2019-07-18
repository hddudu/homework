package com.hongdu.gupao.chain.price;

/**
 * @ClassName PriceStrategy
 * @Description 价格策略
 * @Author dudu
 * @Date 2019/7/18 16:47
 * @Version 1.0
 */
public interface PriceStrategy {
    /**
     * 结算价格
     * @return
     */
    double calcPrice(double price   );
}
