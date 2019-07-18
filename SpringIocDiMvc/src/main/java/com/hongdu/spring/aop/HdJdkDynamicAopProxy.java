package com.hongdu.spring.aop;

import com.hongdu.spring.aop.invocations.HdReflectiveMethodInvocation;
import com.hongdu.spring.aop.support.HdAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @ClassName HdJdkDynamicAopProxy
 * @Description 代理目标类对
 * @Author dudu
 * @Date 2019/7/18 15:47
 * @Version 1.0
 */
public class HdJdkDynamicAopProxy implements HdAopProxy, InvocationHandler {

    /**
     * 解析配置文件 获取代理的相关信息
     */
    private HdAdvisedSupport advised;

    /**
     * 首先可以获取到一个目标类
     * @param advised
     */
    public HdJdkDynamicAopProxy(HdAdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() throws Exception {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    /**
     * 获取代理对象
     * @param classLoader
     * @return
     * @throws Exception
     */
    @Override
    public Object getProxy(ClassLoader classLoader) throws Exception {
        //根据接口 类加载器 jdk的反射调用器 ： 实现获取代理对象
        return Proxy.newProxyInstance(classLoader,
                                    this.advised.getTargetClass().getInterfaces(),
                                    this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取可以应用到此方法上的Interceptor列表
        /**
         *     * @param proxy  代理对象
         *      * @param target  目标对象 : instance = clazz.newInstance;
         *      * @param method 方法
         *      * @param arguments 参数
         *      * @param targetClass 目标类
         *      * @param interceptorsAndDynamicMethodMatchers 拦截和代理方法匹配 列表---》 用来迭代的
         *      执行器链 ： 以方法为key，  以配置的 切面 + 方法 也就是 before  + add  + after 为value ， 去执行这个链方法；
         *
         *      interceptor : 继承了 advice ： 通知 ： 切入点 ： 方法
         *      多个方法
         *      拦截器链 通过解析配置 拿到
         */
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());
        //获取到拦截器链路
        HdReflectiveMethodInvocation invocation = new HdReflectiveMethodInvocation(
                proxy, this.advised.getTarget(), method, args, this.advised.getTargetClass(), chain);
        //拦截器链的调用
        return invocation.proceed();
    }
}
