package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.Method;

public class GPMeiPo implements GPInvocationHandler {

    private Object target;

    public Object getInstance(Object target) throws Exception {
        this.target = target;
        Class<?> clazz = target.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(this.target,args);
        return null;
    }
}
