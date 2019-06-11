package com.hongdu.gupao.proxy.dbrouter;

/**
 * 根据年份进行分库分表
 */
public class Order {

    private Object orderInfo;
    //订单创建时间 按年进行分库
    private Long createTime;

    private String id;

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
