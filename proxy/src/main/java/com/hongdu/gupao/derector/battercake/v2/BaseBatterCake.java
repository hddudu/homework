package com.hongdu.gupao.derector.battercake.v2;

/**
 * @ClassName BaseBatterCake
 * @Description 原装 ： 被修饰的 煎饼类
 *      一个煎饼 5元
 *      待修饰
 *      待增强的类
 *          Excutor 执行器
 *
 * @Author dudu
 * @Date 2019/7/2 17:30
 * @Version 1.0
 */
public class BaseBatterCake extends AbstractBatterCake {

    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
