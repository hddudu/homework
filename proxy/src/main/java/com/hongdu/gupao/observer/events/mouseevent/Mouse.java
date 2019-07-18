package com.hongdu.gupao.observer.events.mouseevent;

import com.hongdu.gupao.observer.events.core.EventListener;

/**
 * @ClassName Mouse
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 3:43
 * @Version 1.0
 */
public class Mouse extends EventListener {

    public void click(){
        System.out.println("调用单击方法");
        this.trigger(MouseEventType.ON_CLICK);
    }


}

