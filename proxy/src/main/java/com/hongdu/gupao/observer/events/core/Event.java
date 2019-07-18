package com.hongdu.gupao.observer.events.core;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @ClassName Event
 * @Description 监听器的y一种 包装 ： 标准事件源 格式 的定义
 *
 *  : 根据成员属性： 在什么时候 time
 *                  某事件源 source
 *                   发生了一个动作事件， 就是一个事件发生了 method
 *                   发生后，我们要做了一些事情， 然后要通知别人 target
 *
 *                   在监听器里面： 这个 事件和方法的绑定是必须要有的， 然后再是目标 事件源
 * @Author dudu
 * @Date 2019/7/18 3:31
 * @Version 1.0
 */
@Data
public class Event {

    //事件源 本身 ： event自身对象
    private Object source;
    //事件触发，要通知谁
    private Object target;

    //事件触发，要做什么动作，回调
    private Method callback;

    //事件的名称，触发的是什么事件
    private String trigger;

    private long time;

    /**
     * 目标对象 和 回调方法 是必须要传的参数
     * @param target 目标对象
     * @param callback 回调的方法
     */
    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    /**
     * 设置触发器
     * @param trigger
     * @return
     */
    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }
}
