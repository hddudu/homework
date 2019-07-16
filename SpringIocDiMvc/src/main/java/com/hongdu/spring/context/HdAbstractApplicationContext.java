package com.hongdu.spring.context;

/**
 * @ClassName HdAbstractApplicationContext
 * @Description 抽象一个类 ： 提供了一个空方法 等子类实现
 *  为什么要这么做？
 * @Author dudu
 * @Date 2019/7/16 17:02
 * @Version 1.0
 */
public abstract class HdAbstractApplicationContext {

    /**
     * 受保护的方法 ： 外部无法调用
     * @throws Exception
     */
    protected void refresh() throws Exception{

    }
}
