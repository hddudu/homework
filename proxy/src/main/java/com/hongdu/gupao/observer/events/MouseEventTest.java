package com.hongdu.gupao.observer.events;

import com.hongdu.gupao.observer.events.mouseevent.Keyboard;
import com.hongdu.gupao.observer.events.mouseevent.Mouse;
import com.hongdu.gupao.observer.events.mouseevent.MouseEventCallback;
import com.hongdu.gupao.observer.events.mouseevent.MouseEventType;

/**
 * @ClassName MouseEventTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 3:31
 * @Version 1.0
 */
public class MouseEventTest {

    public static void main(String[] args) {
        //定义 回调方法 ： 用于反射调用方法 ： 内部装有一些成员方法
        //1: 统一回调方法
        MouseEventCallback callback = new MouseEventCallback();

        //鼠标对象 ： 继承了事件监听器（注册事件对象 ： ）
        // 添加事件监听器
        //注册事件： 将事件类型 和 标准事件源 做一个一一匹配
        //触发器 ： 触发器里面进行了 反射调用 ： 调用到了回调方法
        //2: 注册的事件对象
        Mouse mouse = new Mouse();
        Keyboard keyboard = new Keyboard();

        //添加事件监听器 ： 初始化话传入了 事件源对象 和 回调方法
        //3: 在源事件上添加监听事件
        mouse.addEventListener(MouseEventType.ON_CLICK,callback);
        keyboard.addEventListener(MouseEventType.ON_UP,callback);

        //4： 调用方法 ： 回调方法
        mouse.click();
        keyboard.up();

        /**
         * 总结： 1： 事件源标准格式定义； （基本定义）
         *              定义了： 目标对象
         *                      回调方法
         *                      触发回调方法名 ： trigge
         *                      设置回调方法
         *       2： 事件监听器 （顶层被观察）
         *             定义了 ： 容器 ：触发回调方法名 = 事件源标准格式
         *                       注册 到容器中
         *                       触发器 方法 ： 在回调的时候调用这个方法
         *       3： 事件回调器 （MouseEventCallback）
         *             定义了 ： 定义了回调方法 ： （这个方法在事件源中设置了）
         *                          因此可以在 观察者中 进行反射调用
         *       4： 事件类型 （枚举）
         *             定义了基本的事件类型 ： click up down 等等
         *       5： 注册回调方法的 ： 源目标 （真实的被观察者）
         *              mouse ：
         *              keyboard ：
         */
    }
}
