package com.hongdu.gupao.derector.person;

/**
 * @ClassName Man
 * @Description  男人在吃
 * @Author dudu
 * @Date 2019/6/28 19:04
 * @Version 1.0
 */
public class Man implements Person {

    @Override
    public void eat() {
        System.out.println("男人在吃饭!");
    }
}
