package com.hongdu.gupao.observer.events.core;

import com.hongdu.gupao.observer.events.mouseevent.MouseEventType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EventListener
 * @Description 事件监听器 ： 相当于观察者 ： 注册在 事件源上面
 *  A tagging interface that all event listener interfaces must extend.
 * @Author dudu
 * @Date 2019/7/18 3:36
 * @Version 1.0
 */
public class EventListener {

    //JDK底层的Lisenter通常也是这样来设计的
    protected Map<String, Event> events = new HashMap<>();
    //java.util.EventListener

    //事件名称和一个目标对象来触发事件
    //事件名称触发
    protected void trigger(String  trigger) {
        if(!this.events.containsKey(trigger)) {return;}
        //设置触发器 ： 给事件对象设置触发器
        Event eventTrigger = this.events.get(trigger).setTrigger(trigger);
        //trigger(eventTrigger);
        //暂时不设置这个值可以吗？ 这个是可以的！！！
//        eventTrigger.setSource(this);
        eventTrigger.setTime(System.currentTimeMillis());
        try {
            Method method = eventTrigger.getCallback();
            //发起回调
            if(method != null) {
                //用反射调用它的函数
                method.invoke(eventTrigger.getTarget(), eventTrigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注册事件
    /**
     * @param eventType 事件类型
     * @param target 这个是回调对象 ： MouseEventCallback 鼠标事件回调
     */
    public void addEventListener(String eventType, Object target) {
        try {
            //获取 回调方法 :
            Method callback = target.getClass().getMethod("on" + toUpperFirstCase(eventType), Event.class);
            Event event = new Event(target, callback);
            //事件类型
            events.put(eventType, event);
//            this.addEventListener(eventType, target, target.getClass().getMethod("on" + toUpperFirstCase(eventType), Event.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 其实还是在本来的方法里面调用了自己的方法
     *  然后自己的方法进行了反射调用其它的方法
     */
    public void up(){
        System.out.println("调用弹起方法");
        this.trigger(MouseEventType.ON_UP);
    }

//    public void addEventListener(String eventType, Object target, Method callback) {
//        //注册事件 : 事件类型 = （事件源和回调方法）
//        events.put(eventType, new Event(target, callback));
//    }

    //触发，只要有动作就触发
//    private void trigger(Event event) {
//        event.setSource(this);
//        event.setTime(System.currentTimeMillis());
//        try {
//            //发起回调
//            if(event.getCallback() != null) {
//                //用反射调用它的函数
//                event.getCallback().invoke(event.getTarget(), event);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private String toUpperFirstCase(String string) {
        char[] chars = string.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

}
