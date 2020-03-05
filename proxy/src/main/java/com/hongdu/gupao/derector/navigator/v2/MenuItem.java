package com.hongdu.gupao.derector.navigator.v2;

/**
 * @ClassName MenuItem
 * @Description 菜单元素
 * @Author dudu
 * @Date 2020/3/5 14:30
 * @Version 1.0
 */
public class MenuItem implements Comparable<MenuItem> {
    private String name;
    private Integer orderNo;
    public void setName(String name) {
        this.name = name;
    }

    public MenuItem(String name, Integer orderNo) {
        this.name = name;
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public MenuItem() {
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }

    /**
     *  1
     *  2
     * @param o
     * @return
     */
    @Override
    public int compareTo(MenuItem o) {
        return (this.orderNo - o.orderNo);
    }
}
