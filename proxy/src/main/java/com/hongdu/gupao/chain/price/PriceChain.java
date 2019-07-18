package com.hongdu.gupao.chain.price;

/**
 * @ClassName PricePrivilege
 * @Description 定义特殊价格的优惠策略接口，用于特殊价格优惠策略的链式处理；next() 方法在这里是责任链模式实现的关键；
 * @Author dudu
 * @Date 2019/7/18 16:52
 * @Version 1.0
 */
public interface PriceChain extends PriceStrategy{
    /**
     *  这里得到 这个责任链对象 ：
     *   返回这个接口本身
     *   然后注册 这个接口
     * @return
     */
    PriceChain next();

    /**
     * 注册下一个 本身的接口的实现类
     * @param goNext
     */
    void register(PriceChain goNext);
}
