package com.hongdu.gupao.derector.navigator.v2;

import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName AbstractMenu
 * @Description 抽象菜单 属性 方法
 * @Author dudu
 * @Date 2020/3/5 14:27
 * @Version 1.0
 */
public abstract class AbstractMenu {

//    protected String menus = "";
    protected Set<MenuItem> menuItems = new TreeSet<>();

    //导航栏 菜单栏 ---》 打印
    public abstract void printMenus();

    public abstract void addMenus();

}
