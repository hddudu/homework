package com.hongdu.gupao.observer.guawa;

import com.google.common.eventbus.Subscribe;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 9:44
 * @Version 1.0
 */
public class Teacher {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    /**
     * 这里竟然还限制了只有一个参数
     * @param question
     */
    @Subscribe
    public void explainQuestion(Question question) {
        System.out.println("==============================");
        System.out.println(this.name + "老师,你好!\n");
        System.out.println("您收到了y一个来自:\"" + Gper.name + "\"的提问,x希望您能解答,问题内容如下:\n" +
                question.getContent() + "\n"
                + "提问者: " + question.getUserName());
    }
}
