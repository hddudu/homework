package com.hongdu.gupao.derector.navigator.v2;

import java.util.TreeSet;

/**
 * @ClassName BaseMenu
 * @Description 基本菜单
 * @Author dudu
 * @Date 2020/3/5 14:29
 * @Version 1.0
 */
public class BaseMenu extends AbstractMenu {

    public BaseMenu() {
        this.menuItems.add(new MenuItem("问答",1));
        this.menuItems.add(new MenuItem("文章",2 ));
        this.menuItems.add(new MenuItem("精品课", 6));
        this.menuItems.add(new MenuItem("冒泡", 7));
        this.menuItems.add(new MenuItem("商城", 8));
    }

    @Override
    public void printMenus() {
        this.menuItems.forEach(t -> System.out.print("【" + t.getName() + "】 "));
    }

    @Override
    public void addMenus() {

    }
}
