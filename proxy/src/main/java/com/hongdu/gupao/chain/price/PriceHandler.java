package com.hongdu.gupao.chain.price;

/**
 * @ClassName PriceHandler
 * @Description 价格处理者接口，接口规定：价格的最终结算价格将在这个接口中处理完成；用户不需要关心如何完成，
 * 以及是否使用了价格的策略，相当于将所有的价格优惠策略都封装和隐藏起来，实现与客户端的分离
 *
 * 提供对商品价格的计算方式
 *
 * @Author dudu
 * @Date 2019/7/18 17:51
 * @Version 1.0
 */
public interface PriceHandler {

    /**
     * 计算价格： 根据不同的优惠策略进行不同的计算
     * @param price
     * @param ps
     * @return
     */
    double calcPrice(double price, PriceChain ps);
}
