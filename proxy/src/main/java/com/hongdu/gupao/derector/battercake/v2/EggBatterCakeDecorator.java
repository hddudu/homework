package com.hongdu.gupao.derector.battercake.v2;

/**
 * @ClassName EggBatterCakeDecorator
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/11 13:55
 * @Version 1.0
 */
public class EggBatterCakeDecorator extends AbstractBatterCakeDecorator {

    public EggBatterCakeDecorator(AbstractBatterCake abstractBatterCake) {
        super(abstractBatterCake);
    }

    @Override
    protected void enhanceMethod() {

    }

    @Override
    protected String getMsg() {
        return super.getMsg() + " + 1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}
