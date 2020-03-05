package com.hongdu.gupao.derector.navigator.v2;

/**
 * @ClassName VipMenuDecorator
 * @Description vip 装饰 菜单
 * @Author dudu
 * @Date 2020/3/5 14:40
 * @Version 1.0
 */
public class AdministratorMenuDecorator extends AbstractMenuDecorator {

    public AdministratorMenuDecorator(AbstractMenu abstractMenu) {
        super(abstractMenu);
    }

    @Override
    public void addMenus() {
        abstractMenu.menuItems.add(new MenuItem("作业", 3));
        abstractMenu.menuItems.add(new MenuItem("题库", 4));
        abstractMenu.menuItems.add(new MenuItem("成长墙", 5));
        abstractMenu.menuItems.add(new MenuItem("学生管理", 9));
        abstractMenu.menuItems.add(new MenuItem("老师管理", 10));

    }
}
