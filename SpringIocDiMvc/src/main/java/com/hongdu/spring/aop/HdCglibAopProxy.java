package com.hongdu.spring.aop;

import com.hongdu.spring.aop.support.HdAdvisedSupport;

/**
 * @ClassName CglibAopProxy
 * @Description cglib 代理
 * @Author dudu
 * @Date 2019/7/18 15:46
 * @Version 1.0
 */
public class HdCglibAopProxy implements HdAopProxy {

    public HdCglibAopProxy() {

    }

    public HdCglibAopProxy(HdAdvisedSupport config) {

    }

    @Override
    public Object getProxy() throws Exception {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) throws Exception {
        return null;
    }
}
