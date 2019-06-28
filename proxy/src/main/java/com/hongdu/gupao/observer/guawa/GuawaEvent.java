package com.hongdu.gupao.observer.guawa;

import com.google.common.eventbus.Subscribe;

/**
 * @ClassName GuawaEvent
 * @Description 事件总线 :
 * @Author dudu
 * @Date 2019/6/28 9:39
 * @Version 1.0
 */
public class GuawaEvent {

    @Subscribe
    public void subscribe(String str) {
        System.out.println("执行subscribe方法，传入的参数是：" + str);
    }
}
