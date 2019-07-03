package com.hongdu.gupao.derector.battercake.v1;

/**
 * @ClassName BatterCakeWithEgg
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/2 16:08
 * @Version 1.0
 */
public class BatterCakeWithEgg extends BatterCake {

    @Override
    protected String getMsg() {
        return super.getMsg() + "+ 一个鸡蛋";
    }

    /**
     * 加一个鸡蛋 2块钱
     * @return
     */
    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
