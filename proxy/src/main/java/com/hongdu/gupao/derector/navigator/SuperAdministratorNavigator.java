package com.hongdu.gupao.derector.navigator;

/**
 * @ClassName AdministratorNavigator
 * @Description 管理员
 * @Author dudu
 * @Date 2020/3/5 13:31
 * @Version 1.0
 */
public class SuperAdministratorNavigator extends AbstractNavigator {

    @Override
    public void addNavigatorItem() {
        NavigatorItem navigatorItem3 = new NavigatorItem("作业", 3);
        NavigatorItem navigatorItem = new NavigatorItem("题库", 4);
        NavigatorItem navigatorItem1 = new NavigatorItem("成长墙", 5);
        NavigatorItem navigatorItem4 = new NavigatorItem("学生管理", 9);
        NavigatorItem navigatorItem5 = new NavigatorItem("老师管理", 10);
        NavigatorItem navigatorItem6 = new NavigatorItem("管理员管理", 11);
        getNavigatorItems().add(navigatorItem);
        getNavigatorItems().add(navigatorItem3);
        getNavigatorItems().add(navigatorItem1);
        getNavigatorItems().add(navigatorItem4);
        getNavigatorItems().add(navigatorItem5);
        getNavigatorItems().add(navigatorItem6);
    }
}
