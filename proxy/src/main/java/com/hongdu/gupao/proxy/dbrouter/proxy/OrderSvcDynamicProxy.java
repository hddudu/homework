package com.hongdu.gupao.proxy.dbrouter.proxy;

import com.hongdu.gupao.proxy.dbrouter.db.DynamicDataSourceEntity;
import com.hongdu.gupao.proxy.dynamicproxy.gpproxy.GPClassLoader;
import com.hongdu.gupao.proxy.dynamicproxy.gpproxy.GPInvocationHandler;
import com.hongdu.gupao.proxy.dynamicproxy.gpproxy.GPProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GPProxy :鼓泡代理
 * GPInvocationHandler ： 鼓泡反射处理 自动调用方法
 * GPClassLoader ： 鼓泡类加载器
 */
public class OrderSvcDynamicProxy implements GPInvocationHandler {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private Object proxyObject;//代理对象

    //传入一个对象： 根据这个对象生成代理对象，Proxy可以根据这个对象： 获取到它的ClassLoader interfaces
    public Object getInstance(Object sourceTarget) {
        this.proxyObject = sourceTarget;
        Class<?> clazz = sourceTarget.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(args);//[Ljava.lang.Object;@26653222
//        System.out.println(args[0]);//com.hongdu.gupao.proxy.dbrouter.Order@3532ec19
//        System.out.println(args.length);//1
        before(args[0]);//参数 args[0]
        Object obj = method.invoke(proxyObject, args); // 创建订单 createOrder(order); 就是这个参数了
        after();
        return obj;
    }

    //切换数据源 : sourceTarget : order对象 ：
    private void before(Object target) {
        try {
            //进行数据源的切换
            System.out.println("Proxy before method :");
            //约定优先于配置
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));

            System.out.println("动态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据！");
            //切换数据源
            DynamicDataSourceEntity.set(dbRouter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //还原数据源
    private void after() {
        System.out.println("Proxy after method :");
        //还原成默认的数据源
        DynamicDataSourceEntity.clear();
    }
}
