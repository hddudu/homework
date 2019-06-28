package com.hongdu.gupao.observer.guawa;

/**
 * @ClassName GuawaTeacherTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 9:49
 * @Version 1.0
 */
public class GuawaTeacherTest {
    public static void main(String[] args) {

        Gper gper = new Gper();//继承了EventBus
        Question question = new Question();
        question.setUserName("dudu");
        question.setContent("观察者模式是这样子的吗?");
        Teacher tomTeacher = new Teacher("tomTeacher");
        gper.register(tomTeacher);
        gper.post(question);
    }
}
