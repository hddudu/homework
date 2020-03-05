package com.hongdu.gupao.derector.navigator;

/**
 * @ClassName TeacherNavigator
 * @Description 老师
 * @Author dudu
 * @Date 2020/3/5 13:31
 * @Version 1.0
 */
public class TeacherNavigator extends AbstractNavigator {

    @Override
    public void addNavigatorItem() {
        NavigatorItem navigatorItem3 = new NavigatorItem("作业", 3);
        NavigatorItem navigatorItem = new NavigatorItem("题库", 4);
        NavigatorItem navigatorItem1 = new NavigatorItem("成长墙", 5);
        NavigatorItem navigatorItem4 = new NavigatorItem("学生管理", 9);
        getNavigatorItems().add(navigatorItem);
        getNavigatorItems().add(navigatorItem3);
        getNavigatorItems().add(navigatorItem1);
        getNavigatorItems().add(navigatorItem4);
    }
}
