package com.hongdu.gupao.spring.spring.context;

/**
 * @ClassName AbstractHdApplicationContext
 * @Description 抽象实现方法 ： 顶层都放一层接口
 *  IOC容器的顶层设计
 * @Author dudu
 * @Date 2019/7/14 21:05
 * @Version 1.0
 */
public abstract class HdAbstractApplicationContext  {

    /**
     * 受保护的， 只提供给子类去重写 ， 父类无法调用
     * 使用了空实现 ： 所以子类无需进行继承 或者实现
     */
    protected void refresh() throws Exception {

    }
}
