package com.hongdu.gupao.observer.myobserver;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/27 19:26
 * @Version 1.0
 */
public class Teacher implements Observer {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        GperCommunity gper = (GperCommunity) o;//观察著的主题
        //参数就是args Question
        Question question = (Question) arg;
        System.out.println("==============================");
        System.out.println(name + "老师,你好!\n");
        System.out.println("您收到了y一个来自:\"" + gper.getName() + "\"的提问,x希望您能解答,问题内容如下:\n" +
              question.getContent() + "\n"
            + "提问者: " + question.getUserName());
    }
}
