package com.hongdu.gupao.proxy.staticproxy.girlsproxy;

import com.hongdu.gupao.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 : 实现 InvocationHandler接口 : 这个自动调用findLover方法
 * Proxy
 */
public class JDKMeiPo implements InvocationHandler {

    private Object target;//代理目标对象

//    public JDKMeiPo(Object obj) {//代理的是: Person
//        this.target = obj;
//    }
    //传入目标对象生成代理对象
    public Object getInstance(Object target) throws Exception{
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * invoke方法是自动调用： 不用手动调用
     * @param proxy 生成的代理对象
     * @param method 方法:: 作为一个参数, 接口中有什么方法, 这里就调用什么方法
     * @param args 参数 参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //媒婆筛选
        before();
        Object obj = method.invoke(this.target,args);//目标对象 反射调用 : proxy是代理对象
        //中意后: 牵线
        after();
        return null;
    }

//    private void before(Method method) {
//        /* if(method.e) */
//    }

    private void before() {
        System.out.println("我是媒婆,我要给你找对象，现在已经确认你的需求！");
        System.out.println("开始物色");
    }

    private void after() {
        System.out.println("OK的话，开始办事！");
    }
}
