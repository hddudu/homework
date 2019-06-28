package com.hongdu.gupao.observer.guawa;

import com.google.common.eventbus.EventBus;

/**
 * @ClassName GuawaEventTest
 * @Description
 *   guawa实现问题通知:
 *     通知的消息是Question
 *     观察者: 老师观察社区
 *     社区: 被观察者: 继承了总线
 * @Author dudu
 * @Date 2019/6/28 9:40
 * @Version 1.0
 */
public class GuawaEventTest {

    public static void main(String[] args) {
        //消息总线
        EventBus eventBus = new EventBus();
        GuawaEvent guawaEvent = new GuawaEvent();

        eventBus.register(guawaEvent);
        eventBus.post("tom");
        //从Struts到SpringMVC的升级
        //因为Struts面向的类，而SpringMVC面向的是方法

        //前面两者面向的是类，Guava面向是方法

        //能够轻松落地观察模式的一种解决方案
        //MQ
    }
}
