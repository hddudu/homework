package com.hongdu.gupao.prototype;

/**
 * @ClassName OrderItem
 * @Description TODO
 * @Author dudu
 * @Date 2020/3/1 15:48
 * @Version 1.0
 */
public class OrderItem {
    @Override
    public String toString() {
        return "OrderItem [name=" + name + ", price=" + price + "]";
    }

    private String name;
    private double  price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
