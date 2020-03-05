package com.hongdu.gupao.derector.navigator.v2;

/**
 * @ClassName MenuItemBuilder
 * @Description 构建 MenuItem 并可以设置排序
 *      使用静态内部类完成 建造者模式
 *      sql 构造可以使用
 * @Author dudu
 * @Date 2020/3/5 15:10
 * @Version 1.0
 */
public class MenuItemBuilder {
    private MenuItem menuItem = new MenuItem();

    //提供的是 构建的步骤
    public MenuItemBuilder setName(String name) {
        menuItem.setName(name);
        return this;
    }

    public MenuItemBuilder setOrderNo(Integer orderNo) {
        menuItem.setOrderNo(orderNo);
        return this;
    }

    //提供一个公共的方法 : 构建对象的方法来返回
    public MenuItem build() {
        return this.menuItem;
    }
}
