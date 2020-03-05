package com.hongdu.gupao.derector.navigator.v2;

/**
 * @ClassName VipMenuDecorator
 * @Description vip 装饰 菜单
 * @Author dudu
 * @Date 2020/3/5 14:40
 * @Version 1.0
 */
public class VipMenuDecorator extends AbstractMenuDecorator {

    public VipMenuDecorator(AbstractMenu abstractMenu) {
        super(abstractMenu);
    }

    @Override
    public void addMenus() {
        super.menuItems.add(new MenuItem("作业", 3));
        super.menuItems.add(new MenuItem("题库", 4));
        super.menuItems.add(new MenuItem("成长墙", 5));
    }
}
