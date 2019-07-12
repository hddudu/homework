package com.hongdu.gupao.derector.battercake.v2;

/**
 * @ClassName SausageBatterCakeDecorator
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/11 13:56
 * @Version 1.0
 */
public class SausageBatterCakeDecorator extends AbstractBatterCakeDecorator {

    @Override
    protected void enhanceMethod() {
        System.out.println("增强方法 ： 装饰 ： 添加门面功能！");
    }

    public SausageBatterCakeDecorator(AbstractBatterCake abstractBatterCake) {
        super(abstractBatterCake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+ 一根热狗";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 3;
    }
}
