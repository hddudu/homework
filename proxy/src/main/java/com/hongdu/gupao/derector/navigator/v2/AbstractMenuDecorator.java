package com.hongdu.gupao.derector.navigator.v2;

/**
 * @ClassName AbstractMenuDecorator
 * @Description TODO
 * @Author dudu
 * @Date 2020/3/5 14:29
 * @Version 1.0
 */
public class AbstractMenuDecorator extends AbstractMenu {

    //持有自己父类的引用 ： 能够找到自己要装饰的原对象 ： 这个是属于 ： java 的上溯造型的原理
    private AbstractMenu abstractMenu;

    //注入对象： 注入原对象
    public AbstractMenuDecorator(AbstractMenu abstractMenu) {
        this.abstractMenu = abstractMenu;
    }

    @Override
    public void printMenus() {
        addMenus();
        abstractMenu.printMenus();
    }

    @Override
    public void addMenus() {
        abstractMenu.addMenus();
    }
}
