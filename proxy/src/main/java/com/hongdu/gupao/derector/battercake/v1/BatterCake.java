package com.hongdu.gupao.derector.battercake.v1;

/**
 * @ClassName BatterCake
 * @Description 煎饼类
 * @Author dudu
 * @Date 2019/7/2 16:07
 * @Version 1.0
 */
public class BatterCake {

    /**
     * 买一个煎饼
     * @return
     */
    protected String getMsg() {
        return "煎饼";
    }

    /**
     * 一个煎饼5块钱
     * @return
     */
    public int getPrice() {
        return 5;
    }
}
