package com.hongdu.gupao.chain;

import com.hongdu.gupao.chain.price.PriceChain;

/**
 * @ClassName AbstractPriceChain
 * @Description 抽象类，实现对象链的选择和注册；
 * @Author dudu
 * @Date 2019/7/18 16:55
 * @Version 1.0
 */
public abstract class AbstractPriceChain implements PriceChain {

    protected PriceChain next;

    /**
     * 将这个对象的指向下一个 这个对象的实现
     *  相当于一个节点拼接一样
     * @param goNext
     */
    @Override
    public void register(PriceChain goNext) {
        this.next = goNext;
    }

    @Override
    public PriceChain next() {
        return this.next;
    }


}
