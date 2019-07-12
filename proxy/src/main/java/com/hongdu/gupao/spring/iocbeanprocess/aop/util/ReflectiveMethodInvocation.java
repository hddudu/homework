package com.hongdu.gupao.spring.iocbeanprocess.aop.util;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectiveMethodInvocation
 * @Description 反射方法 调用 链
 * @Author dudu
 * @Date 2019/7/12 14:58
 * @Version 1.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    /**
     * 内部类继承了这个类后 ： 可以直接使用这个类的属性
     */
    protected Object target;

    /**
     * 内部类继承了这个类后 ： 可以直接使用这个类的属性
     */
    protected Method method;

    /**
     * 内部类继承了这个类后 ： 可以直接使用这个类的属性
     */
    protected Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
