package com.hongdu.gupao.derector.battercake.v2;

/**
 * @ClassName AbstractBatterCakeDecorator
 * @Description  装饰类 ：
 *      抽象装饰类
 *      可以通过继承该装饰对原装对象增强
 *          所以抽象装饰类需要持有 原装对象
 *          因为原装对象和抽象装饰类都继承了同一个抽象类 或者实现了一个接口
 *
 *          所以可以通过接口 或者抽象类的引用来进行指向
 *
 *          和原装类一样，实现了抽象的方法， 持有原来类继承的抽象类的引用，所以其实就是原装类的借用
 *
 * //静态代理，委派
 * @Author dudu
 * @Date 2019/7/2 17:32
 * @Version 1.0
 */
public abstract class AbstractBatterCakeDecorator extends AbstractBatterCake{

    //静态代理，委派
    private AbstractBatterCake abstractBatterCake;

    //注入实例
    public AbstractBatterCakeDecorator(AbstractBatterCake abstractBatterCake) {
        this.abstractBatterCake = abstractBatterCake;
    }

    //添加一个扩展 ： 增强的方法
    protected abstract void enhanceMethod();

    @Override
    protected String getMsg() {
        return this.abstractBatterCake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.abstractBatterCake.getPrice();
    }
}
