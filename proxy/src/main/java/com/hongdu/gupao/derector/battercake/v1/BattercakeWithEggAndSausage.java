package com.hongdu.gupao.derector.battercake.v1;

/**
 * @ClassName BattercakeWithEggAndSausage
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/2 16:11
 * @Version 1.0
 */
public class BattercakeWithEggAndSausage extends BatterCakeWithEgg {
    @Override
    protected String getMsg() {
        return super.getMsg() + "+ 一根热狗";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 3;
    }
}
