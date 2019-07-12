package com.hongdu.gupao.spring.iocbeanprocess.aop;

import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.AopProxy;

/**
 * @ClassName ProxyFactory
 * @Description 代理工厂
 * @Author dudu
 * @Date 2019/7/12 14:51
 * @Version 1.0
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {


    @Override
    public Object getProxy() {
        return createAopProxy();
    }

    /**
     * 创建一个 Aop 代理
     *      1： jdk代理对象
     *      2： cglib代理
     * @return
     */
    protected final AopProxy createAopProxy() {
        return new Cglib2AopProxy(this);
    }

}
