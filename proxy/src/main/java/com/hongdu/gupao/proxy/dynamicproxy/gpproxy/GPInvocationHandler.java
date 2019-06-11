package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.Method;

/**
 * gupao 反射调用处理器
 */
public interface GPInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;

}
