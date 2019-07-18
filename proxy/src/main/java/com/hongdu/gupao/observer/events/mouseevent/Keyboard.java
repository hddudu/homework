package com.hongdu.gupao.observer.events.mouseevent;

import com.hongdu.gupao.observer.events.core.EventListener;

/**
 * @ClassName Keyboard
 * @Description
 * @Author dudu
 * @Date 2019/7/18 3:43
 * @Version 1.0
 */
public class Keyboard extends EventListener {



    public void up(){
        System.out.println("调用弹起方法");
        this.trigger(MouseEventType.ON_UP);
    }
}
