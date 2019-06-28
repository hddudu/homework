package com.hongdu.gupao.derector.person;

/**
 * @ClassName Decorator
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 19:05
 * @Version 1.0
 */
public abstract class Decorator  implements Person{

    protected Person person;

    /**
     * 注入实例: 采用了setter方法
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 实例化什么 Person的子类进去就是调用了这个子类的方法
     */
//    public Decorator(Person person) {
//        this.person = person;
//    }

    @Override
    public void eat() {
        person.eat();
    }
}
