package com.hongdu.gupao.derector.navigator;

/**
 * @ClassName NormalUserNavigator
 * @Description 普通用户
 * @Author dudu
 * @Date 2020/3/5 13:31
 * @Version 1.0
 */
public class NormalUserNavigator extends AbstractNavigator {

    @Override
    public void addNavigatorItem() {
        NavigatorItem navigatorItem = new NavigatorItem("题库", 4);
        getNavigatorItems().add(navigatorItem);
    }
}
