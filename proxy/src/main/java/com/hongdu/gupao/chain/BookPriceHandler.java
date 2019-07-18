package com.hongdu.gupao.chain;

import com.hongdu.gupao.chain.factory.IPriceStrategyFactory;
import com.hongdu.gupao.chain.price.PriceChain;
import com.hongdu.gupao.chain.price.PriceHandler;

/**
 * @ClassName BookPriceHandler
 * @Description
 *  定义一个书记类型商品的价格处理器，
 *
 * 　　1，该类将从用户那里获取用户可以使用的特殊优惠策略，本篇中直接传入了特殊优惠策略的对象，这里是可以改进的：传入特殊优惠策略的满足条件，比如：用户领取了一张优惠卡，就传入该优惠卡的标识即可；
 *
 * 　　2，该类依赖一个与一个价格策略的工厂接口，该类将通过该工厂接口动态的选择一个策略；
 * @Author dudu
 * @Date 2019/7/18 17:53
 * @Version 1.0
 */
public class BookPriceHandler implements PriceHandler {

    //工厂类
    private IPriceStrategyFactory ipsf;

    //使用这样的门面模式 方便客户端的操作
    public BookPriceHandler() {
        this(new PriceStrategyFactory());
    }

    public BookPriceHandler(IPriceStrategyFactory ipsf) {
        this.ipsf = ipsf;
    }

    /**
     * 这里的优惠是 ：
     *  1： 先进行价格链的优惠， 就是在生日的优惠或者满减100的优惠
     *      无论有没有上面的价格链优惠，都会进行下面的优惠；
     *      如果有上面的优惠，在上面的基础上再进行下面的基础优惠；
     *  2： 然后基础的优惠是 ： 打折：
     *          2.1 打折策略1
     *          2.2 打折策略2
     *          2.3 打折策略3
     *          2.4 默认不打折
     *
     *          \\
     *          \\
     *          \\
     *          \\
     *         下面的这个算法是对的 ， 生日优惠和满减优惠是特权优惠；
     *         然后 那个价格打折优惠是价格上面的一种策略
     * @param price
     * @param ps
     * @return
     */
    @Override
    public double calcPrice(double price, PriceChain ps) {
        //没有策略
       price = privilegePrice(price, ps);
        //特权价格
        price = ipsf.createAndGet(price).calcPrice(price);

        return price;
    }

    /**
     * 优惠方式2 ：
     * 1： 生日优惠 满减优惠 打折优惠都分开来
     *  2： 都是独立的优惠！
     * @param price
     * @param
     * @return
     */
//    @Override
//    public double calcPrice(double price, PriceChain ps) {
//        //没有策略
//       price = privilegePrice(price, ps);
//        //特权价格
//        price = ipsf.createAndGet(price).calcPrice(price);
//
//        return price;
//    }

    private double privilegePrice(double price, PriceChain priceChain) {
        if(null == priceChain) {
            return price;
        }
        return privilegePrice(priceChain.calcPrice(price), priceChain.next());
    }


}
