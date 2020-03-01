package com.hongdu.gupao.prototype;

/**
 * @ClassName JsonTestClone
 * @Description 参考 ： https://my.oschina.net/fengshuzi/blog/418976
 * @Author dudu
 * @Date 2020/3/1 15:30
 * @Version 1.0
 */
public class JsonTestClone {
    public static void main(String[] args) throws Exception {
//        1: 克隆 BeanUtils.copyProperties();
//        2： 克隆 实现 cloneable接口 并实现 序列化接口 （必须实现序列化）
        Order order = new Order();
//        System.out.println(order);
//
//        System.out.println(order.deepClone());
//        结果不一样 ： 所以实现了深克隆
//        com.hongdu.gupao.prototype.Order@387c703b
//        com.hongdu.gupao.prototype.Order@48eff760
//        方式三： 实现json方式:
//        其实java本身的序列化效率其实很低
        System.out.println(order);
        String orderJson = JsonUtils.toJson(order);
//        System.out.println(orderJson);

        Order order1 = JsonUtils.getObjectMapper().readValue(orderJson, Order.class);
        System.out.println(order1);
//        com.hongdu.gupao.prototype.Order@387c703b
//        com.hongdu.gupao.prototype.Order@5d740a0f
    }
}
