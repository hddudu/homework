package com.hongdu.gupao.derector.navigator;

import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName Navigator
 * @Description 导航栏 对象 : 根据权限被装饰的对象
 *            初始化导航值： 问答 文章 精品课 冒泡 商城
 * @Author dudu
 * @Date 2020/3/5 13:21
 * @Version 1.0
 */
public abstract class AbstractNavigator {

    //存在方法区 被多个线程共享 不安全
//    protected static Set<NavigatorItem> navigatorItems;
//    static {
//        navigatorItems = new HashSet<>();
//        //实例化的对象存在堆区
//        navigatorItems.add(new NavigatorItem("问答"));
//        navigatorItems.add(new NavigatorItem("文章"));
//        navigatorItems.add(new NavigatorItem("精品课"));
//        navigatorItems.add(new NavigatorItem("冒泡"));
//        navigatorItems.add(new NavigatorItem("商城"));
//    }
    //上面的静态不予采用 ： 线程不安全啥的 不想用

    protected  Set<NavigatorItem> navigatorItems;

    public AbstractNavigator() {
        navigatorItems = new TreeSet<>();
        //实例化的对象存在堆区
        navigatorItems.add(new NavigatorItem("问答",1));
        navigatorItems.add(new NavigatorItem("文章",2 ));
        navigatorItems.add(new NavigatorItem("精品课", 6));
        navigatorItems.add(new NavigatorItem("冒泡", 7));
        navigatorItems.add(new NavigatorItem("商城", 8));
    }

//    public abstract void addNavigatorItem(NavigatorItem navigatorItem);
    //先使用不传参数的写法
    public abstract void addNavigatorItem();

    public  Set<NavigatorItem> getNavigatorItems() {
        return navigatorItems;
    }

    /**
     *  怎么被弄成了一个模板模式了
     */
    public void printlnNavigators() {
        addNavigatorItem();
        getNavigatorItems().forEach(t -> System.out.print("【" + t.getName() + "】 "));
    }
}
