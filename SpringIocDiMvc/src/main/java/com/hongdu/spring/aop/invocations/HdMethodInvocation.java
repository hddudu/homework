package com.hongdu.spring.aop.invocations;

import com.hongdu.spring.aop.aspect.HdJoinPoint;

import java.lang.reflect.Method;

/**
 * @ClassName HdMethodInvocation
 * @Description 方法 反射调用器
 * @Author dudu
 * @Date 2019/7/18 16:20
 * @Version 1.0
 */
public interface HdMethodInvocation extends HdJoinPoint {
    /**
     * 获取方法
     * @return
     */
    Method getMethod();

    Object proceed() throws Throwable;
}
