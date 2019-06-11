package com.hongdu.gupao.proxy.dbrouter;

//数据库底层 实现类
public class OrderDao {

    public int insert(Order order) {
        System.out.println("订单创建成功!");
        return 1;
    }
}
