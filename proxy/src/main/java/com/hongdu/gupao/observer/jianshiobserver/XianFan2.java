package com.hongdu.gupao.observer.jianshiobserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName XianFan1
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 9:08
 * @Version 1.0
 */
public class XianFan2 implements JsObservalbe{
    private String name = "明哥";

    //添加监视人集合
    private List<JsObserver> observers = new ArrayList<>();

    @Override
    public void addJsObserver(JsObserver jsObserver) {
        if(!observers.contains(jsObserver)) {
            observers.add(jsObserver);
        }
    }

    @Override
    public void removeJsObserver(JsObserver jsObserver) {
        if(observers.contains(jsObserver)) {
            observers.remove(jsObserver);
        }
    }

    @Override
    public void notice(String message) {
        //被观察者的通知 :
        //其实就是观察者调用自己的更新状态 信息的方法
        for (JsObserver jsObserver : observers) {
            jsObserver.update(message, name);//观察者在观察者的类里面调用自己的方法
        }
    }
}
