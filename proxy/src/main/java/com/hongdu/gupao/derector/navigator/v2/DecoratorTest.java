package com.hongdu.gupao.derector.navigator.v2;

/**
 * @ClassName DecoratorTest
 * @Description TODO
 * @Author dudu
 * @Date 2020/3/5 14:47
 * @Version 1.0
 */
public class DecoratorTest {

    public static void main(String[] args) {
        BaseMenu baseMenu = new BaseMenu();
        baseMenu.printMenus();

        //对 基本菜单进行装饰
        VipMenuDecorator vipMenuDecorator = new VipMenuDecorator(baseMenu);
        vipMenuDecorator.printMenus();
    }
}
