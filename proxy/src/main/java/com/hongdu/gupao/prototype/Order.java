package com.hongdu.gupao.prototype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.List;

/**
 * @ClassName Order
 * @Description 参考 ： https://my.oschina.net/fengshuzi/blog/418976
 * @Author dudu
 * @Date 2020/3/1 15:48
 * @Version 1.0
 */
public class Order implements Serializable {
//    @Override
//    public String toString() {
//        return "Order [name=" + name + ", orderItems=" + orderItems + "]";
//    }

    private String name;
    private List<OrderItem> orderItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order deepCloenByObjectMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderString = objectMapper.writeValueAsString(this);
        return objectMapper.readValue(orderString, this.getClass());
    }

    public Order deepClone() {
        Order order = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

            ObjectInputStream ois = new ObjectInputStream(bis);
            order = (Order) ois.readObject();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order jsonClone() {
        Order prototypTest = this;
        return  JSON.toJavaObject((JSON) JSONObject.toJSON(prototypTest), Order.class);
    }
}
