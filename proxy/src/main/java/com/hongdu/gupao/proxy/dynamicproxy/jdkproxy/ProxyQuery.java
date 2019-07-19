package com.hongdu.gupao.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyQuery
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 2:13
 * @Version 1.0
 */
public class ProxyQuery implements InvocationHandler {

    private Object target;




    public Object getProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader()
                , this.target.getClass().getInterfaces(), this);
    }
    public ProxyQuery(Object target) {
        this.target = target;
    }
    private Class<?> targetClass;
    public ProxyQuery(Class<?> targetClass) {
        this.targetClass = targetClass;
    }
    public Object getProxy(){
        ClassLoader classLoader = targetClass.getClassLoader();
        return getProxy(classLoader);
    }
    public Object getProxy(ClassLoader classLoader){

        Object object = Proxy.newProxyInstance(classLoader,this.targetClass.getInterfaces(), this);
        return object;
    }
//        ClassLoader classLoader = this.target.getClass().getClassLoader();
    //        Object object = Proxy.newProxyInstance(classLoader,this.target.getClass().getInterfaces(), this);
//    public Object getProxy() {
//
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("在调度真实对象之前的服务");

        Object object = method.invoke(target, args);
        System.out.println(object.toString());

        System.out.println("在调度真实对象之后的服务");
       return object;
    }
}
