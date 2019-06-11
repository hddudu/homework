package com.hongdu.gupao.proxy.dbrouter.proxy;

import com.hongdu.gupao.proxy.dbrouter.IOrderSvc;
import com.hongdu.gupao.proxy.dbrouter.Order;
import com.hongdu.gupao.proxy.dbrouter.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderSvcStaticProxy implements IOrderSvc {

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    private IOrderSvc iOrderSvc;

    //
    public OrderSvcStaticProxy(IOrderSvc iOrderSvc) {
        this.iOrderSvc = iOrderSvc;
    }

    @Override
    public int createOrder(Order order) {

        Long time = order.getCreateTime();

        //年月切换
        /**
         *   public Date(long date) {
         *         fastTime = date;
         *     }
         */
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));

        System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据！");
        //切换数据源
        DynamicDataSourceEntity.set(dbRouter);

        //调用数据库查询
        this.iOrderSvc.createOrder(order);
        //用完后切换回去原来的数据源
        DynamicDataSourceEntity.clear();
        return 0;
    }
}
