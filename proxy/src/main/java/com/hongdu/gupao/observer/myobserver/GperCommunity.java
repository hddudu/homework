package com.hongdu.gupao.observer.myobserver;

import java.util.Observable;

/**
 * @ClassName Question
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/27 19:25
 * @Version 1.0
 */
public class GperCommunity extends Observable {

    private String name = "Gper生态圈";
    private static GperCommunity gper = null;
    private GperCommunity() {}

    public static GperCommunity getInstance() {
        if(gper == null) {
            gper = new GperCommunity();
        }
        return gper;
    }

    public String getName() {
        return name;
    }

    //被观察者:
    //注册观察者 : 新增 删除
    //通知

    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + this.name + "上提交了一个问题");
        setChanged();//状态改变 通知观察者
        notifyObservers(question);//默认通知注册的所有观察者 : 传入问题 传入主题的信息
    }
}
