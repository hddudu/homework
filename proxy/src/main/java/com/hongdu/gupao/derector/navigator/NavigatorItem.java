package com.hongdu.gupao.derector.navigator;

/**
 * @ClassName NavigatorItem
 * @Description 导航元素 ： 属性（名字 + 样式）
 * @Author dudu
 * @Date 2020/3/5 13:23
 * @Version 1.0
 */
public class NavigatorItem implements Comparable<NavigatorItem> {
    private String name;
    private Integer orderNo;

    public NavigatorItem(String name, Integer orderNo) {
        this.name = name;
        this.orderNo = orderNo;
    }

    public NavigatorItem(String name) {
        this.name = name;
    }

    public NavigatorItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     *  1
     *  2
     * @param o
     * @return
     */
    @Override
    public int compareTo(NavigatorItem o) {
        return (this.orderNo - o.orderNo);
    }
}
