package com.hongdu.gupao.proxy.dbrouter;

import com.hongdu.gupao.proxy.dbrouter.proxy.OrderSvcDynamicProxy;
import com.hongdu.gupao.proxy.dbrouter.proxy.OrderSvcStaticProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderSvcTest {

    public static void main(String[] args) {
        try {
            Order order = new Order();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            Date date = sdf.parse("2019/02/01");
            order.setCreateTime(date.getTime());
//            OrderSvcStaticProxy osp = new OrderSvcStaticProxy(new OrderSvcImpl());
//            osp.createOrder(order);//静态代理 ： 代理类 实现真实类实现的接口，并且是相同的方法， 静态代理只能代理这一个类
            //动态代理优势： 实现接口 InvocationHandler；生成代理对象；反射自动调用invoke ：
            // 原始类必须实现接口，代理类需要扫描类中接口才能重新生成代理类，覆盖需要被代理的方法，从而添加增强逻辑，保护
            //生成了代理对象 ： 其实本质是代理类的对象了 $Proxy0 ==>强制转换为原对象 ： implements IOrderSvc : 所以可以通过接口指向
            IOrderSvc obj = (IOrderSvc) new OrderSvcDynamicProxy().getInstance(new OrderSvcImpl());
            obj.createOrder(order);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
