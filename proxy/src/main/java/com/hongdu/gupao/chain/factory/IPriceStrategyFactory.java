package com.hongdu.gupao.chain.factory;

import com.hongdu.gupao.chain.price.PriceStrategy;

/**
 * @ClassName IPriceStrategyFactory
 * @Description PricePrivilege  的工厂实现，
 * 这个工厂类是动态加载和动态选择策略的核心：
 * 　　1, 动态加载策略： 该工厂将从给定的一个目录中，动态加载可用的所有策略，并将这些策略保存到一个链表中；
 *  （并且可以提供一个动态添加和删除策略的接口，
 *      或者在从给定目录中动态加载策略时，启动一个定时检查器，用于间断性添加和删除策略，但这种方法
 *              需要考虑策略的实效性；———————————— 后续思想，本篇未实现；）
 * 　　2，动态策略选择： 每一个策略类都使用了FixedPrivilegePrice 这个注解，本篇该注解使用了两个属性：min 和max ，
 *  表示价格的最大值和最小值，当结算价格在这个区间时，
 *  就选择这种策略；当然这只是简单的价格判断，也可以根据实际添加其他的属性用于策略的选择；
 * @Author dudu
 * @Date 2019/7/18 17:01
 * @Version 1.0
 */
public interface IPriceStrategyFactory {

    /**
     * 根据价格创建优惠策略顶层接口 ：
     *  1： 简单的实现是 ： 固定价格策略工厂
     *  2：                  优惠价格策略工厂
     *                              当优惠价格工厂的时候， 选择： 责任链处理模式
     * @param price
     * @return
     */
    PriceStrategy createAndGet(double price);

}
