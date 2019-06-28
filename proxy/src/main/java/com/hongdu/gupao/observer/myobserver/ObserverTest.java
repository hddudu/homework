package com.hongdu.gupao.observer.myobserver;

/**
 * @ClassName ObserverTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/27 19:49
 * @Version 1.0
 */
public class ObserverTest {
    public static void main(String[] args) {
        GperCommunity gper = GperCommunity.getInstance();
        Question question = new Question();
        question.setUserName("dudu");
        question.setContent("观察者模式是这样子的吗?");
        Teacher tom = new Teacher("tomc");
        gper.addObserver(tom);//生态圈注册了观察者
        gper.publishQuestion(question);
    }
}
