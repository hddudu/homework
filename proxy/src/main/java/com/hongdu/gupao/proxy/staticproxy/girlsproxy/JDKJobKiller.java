package com.hongdu.gupao.proxy.staticproxy.girlsproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 专职业代理 ： 找工作
 */
public class JDKJobKiller implements InvocationHandler {

    private Object target;//代理生成对象

    //可以代理任何对象
    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();//该类的类加载器 :获取对象的类对象 及接口 还有处理器
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);//返回生成的代理对象
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(this.target,args);//
        after();
        return null;
    }

    private void before() {
        System.out.println("人事专员查看审查简历");
    }

    private void after() {
        System.out.println("海量职位匹配，匹配成功，通知面试！");
        System.out.println("面试通过，开始上班！");
    }
}
