package com.hongdu.gupao.spring.iocbeanprocess.aop;

import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.AopProxy;

/**
 * @ClassName AbstractAopProxy
 * @Description 抽象 aop 代理实现类
 * @Author dudu
 * @Date 2019/7/12 14:11
 * @Version 1.0
 */
public abstract class AbstractAopProxy implements AopProxy {

    /**
     * 切面支持类 ： support ： 都是工具支持类
     */
    protected AdvisedSupport advisedSupport;

    public AbstractAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
}
