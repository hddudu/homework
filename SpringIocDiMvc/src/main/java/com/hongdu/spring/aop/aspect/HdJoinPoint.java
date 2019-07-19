package com.hongdu.spring.aop.aspect;

import java.lang.reflect.Method;

/**
 * @ClassName HdJoinPoint
 * @Description 作为一个参数
 * @Author dudu
 * @Date 2019/7/18 22:57
 * @Version 1.0
 */
public interface HdJoinPoint {

    /**
     * 下面的方法 其实也可以直接写在 反射调用方法执行器中 : HdReflectiveMethodInvocation
     * @return
     */
    Object getThis();

    Object[]  getArgs();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);

}
