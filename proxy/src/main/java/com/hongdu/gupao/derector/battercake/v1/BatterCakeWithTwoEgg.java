package com.hongdu.gupao.derector.battercake.v1;

/**
 * @ClassName BatterCakeWithTwoEgg
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/2 16:10
 * @Version 1.0
 */
public class BatterCakeWithTwoEgg extends BatterCakeWithEgg {

    @Override
    protected String getMsg() {
        return super.getMsg() + "+ 1个鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
