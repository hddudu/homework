package com.hongdu.gupao.commission.boss;

public class DelegateTest {

    public static void main(String[] args) {
        //代理模式注重 是过程， 因为过程上，代理模式新生成了代理类
        //客户端 boss 发出客户请求--》 委派者（Leader或者manager） --》被委派者
        //委派者要持有被委派者的引用 ： 类似静态代理的代理类持有真实类的引用一样；
        //委派注重结果
        //委派核心： 分发 调度 派遣 ： 类似于上级派我出差一样；

        //委派就是 静态代理和 策略模式的一种组合
        new Boss().command("登陆", new Manager());
        new Boss().command("画图", new Manager());

    }
}
