package com.hongdu.gupao.proxy.dynamicproxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib : 的代理 方法拦截器
 */
public class CGlibMeiPo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception {
        //相当于Proxy 代理的工具类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);//设置代理类 继承了父类 clazz
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy  方法代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);//Object + objects参数 + 方法代理
        after();
        return null;
    }

    private void before(){
        System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
        System.out.println("开始物色");
    }

    private void after(){
        System.out.println("OK的话，准备办事");
    }

}
