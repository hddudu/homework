package com.hongdu.gupao.derector.person;

/**
 * @ClassName ManDecoratorA
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 19:06
 * @Version 1.0
 */
public class ManDecoratorB extends Decorator {

    @Override
    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }



}
