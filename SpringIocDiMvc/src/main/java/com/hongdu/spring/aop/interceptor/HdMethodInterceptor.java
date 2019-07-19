package com.hongdu.spring.aop.interceptor;

import com.hongdu.spring.aop.invocations.HdMethodInvocation;

/**
 * @ClassName HdMethodInterceptor
 * @Description 方法拦截器接口
 * @Author dudu
 * @Date 2019/7/18 19:00
 * @Version 1.0
 */
public interface HdMethodInterceptor {

    /**
     * 方法拦截器 ： 其实就是方法执行器 ： 在某个方法前面先执行某个方法 （在java里面说是拦截了！）
     * 反射调用器 作为参数
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object invoke(HdMethodInvocation invocation) throws Throwable;
}
