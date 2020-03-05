package com.hongdu.gupao.derector.navigator;

/**
 * @ClassName VipUserNavigator
 * @Description 会员
 * @Author dudu
 * @Date 2020/3/5 13:31
 * @Version 1.0
 */
public class VipUserNavigator extends AbstractNavigator {

    @Override
    public void addNavigatorItem() {
        NavigatorItem navigatorItem3 = new NavigatorItem("作业", 3);
        NavigatorItem navigatorItem = new NavigatorItem("题库", 4);
        NavigatorItem navigatorItem1 = new NavigatorItem("成长墙", 5);
        getNavigatorItems().add(navigatorItem);
        getNavigatorItems().add(navigatorItem3);
        getNavigatorItems().add(navigatorItem1);
    }
}
