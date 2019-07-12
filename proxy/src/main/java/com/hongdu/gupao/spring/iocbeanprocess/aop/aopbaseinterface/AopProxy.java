package com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface;

/**
 * @ClassName AopProxy
 * @Description * AOP代理 对象 的获取
 *
 * @Author dudu
 * @Date 2019/7/12 14:05
 * @Version 1.0
 */
public interface AopProxy {
    /**
     * 获取代理对象 ： 类似 getBean()顶层接口定义
     * @return
     */
    Object getProxy();
}
