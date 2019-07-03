package com.hongdu.gupao.derector.battercake.v1;

import org.junit.Test;

/**
 * @ClassName BatterCakeTest
 * @Description  测试 无限继承法 扩展
 *  这个走下去， 是一个无限扩展的类
 *
 * @Author dudu
 * @Date 2019/7/2 16:13
 * @Version 1.0
 */
public class BatterCakeTest {
    @Test
    public void test() {

        BatterCake batterCake = new BatterCake();
        System.out.println(batterCake.getMsg() + ",总价格 ： " + batterCake.getPrice());

        BatterCakeWithEgg batterCakeWithEgg = new BatterCakeWithEgg();
        System.out.println(batterCakeWithEgg.getMsg() + ",总价格 ： " + batterCakeWithEgg.getPrice());

         BattercakeWithEggAndSausage battercakeWithEggAndSausage = new BattercakeWithEggAndSausage();
        System.out.println(battercakeWithEggAndSausage.getMsg() + ",总价格 ： " + battercakeWithEggAndSausage.getPrice());


    }
}
