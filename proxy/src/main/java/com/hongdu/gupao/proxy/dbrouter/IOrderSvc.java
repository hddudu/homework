package com.hongdu.gupao.proxy.dbrouter;


//服务层接口 ： 类似于动态代理的接口抽象
public interface IOrderSvc {
    int createOrder(Order order);
}
