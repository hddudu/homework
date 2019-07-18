package com.hongdu.spring.aop;

import com.hongdu.spring.aop.support.HdAdvisedSupport;

/**
 * @ClassName HdAopProxyFactory
 * @Description aop 创建工厂类
 * @Author dudu
 * @Date 2019/7/18 16:12
 * @Version 1.0
 */
public interface HdAopProxyFactory {

    /**
    *
     */
    HdAopProxy craeteAopProxy(HdAdvisedSupport config) throws Exception;
}
