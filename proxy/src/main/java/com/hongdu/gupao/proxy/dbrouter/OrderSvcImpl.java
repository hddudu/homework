package com.hongdu.gupao.proxy.dbrouter;

//服务实现层
public class OrderSvcImpl implements IOrderSvc {

    private OrderDao orderDao;//代理了这个

    //不传参数 直接内部实例化
    public OrderSvcImpl() {
        ////使用Spring应该是自动注入的
        orderDao = new OrderDao();
    }

    //构造器初始化 : 传参数
    public OrderSvcImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderServiceImpl调用orderDao创建订单");
        int result = orderDao.insert(order);
        return result;
    }
}
