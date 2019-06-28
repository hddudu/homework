package com.hongdu.gupao.derector.person;

/**
 * @ClassName ManDecoratorA
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 19:06
 * @Version 1.0
 */
public class ManDecoratorA extends Decorator {

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再次一顿饭!");
    }

}
