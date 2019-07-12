package com.hongdu.gupao.derector.battercake.v2;

/**
 * @ClassName V2BatterCakeTest
 * @Description 测试包装器模式
 * @Author dudu
 * @Date 2019/7/11 13:58
 * @Version 1.0
 */
public class V2BatterCakeTest {

    public static void main(String[] args) {

        //顶层抽象类 ： 或者顶层抽象接口
        AbstractBatterCake baseBatterCake;
        //路边买一个原装饼
        //基本对象 ： 原装对象 :
        baseBatterCake = new BaseBatterCake();

        System.out.println(baseBatterCake.getMsg() + "\n 花费： " + baseBatterCake.getPrice());

        System.out.println("一个饼没什么味道， 需要添加一点调料： ");

        //煎饼有点小，想再加一个鸡蛋
        baseBatterCake = new EggBatterCakeDecorator(baseBatterCake);
        System.out.println(baseBatterCake.getMsg() + ",总价：" + baseBatterCake.getPrice());

        System.out.println("再加一个鸡蛋： ");
        baseBatterCake = new EggBatterCakeDecorator(baseBatterCake);
        System.out.println(baseBatterCake.getMsg() + ",总价：" + baseBatterCake.getPrice());
        baseBatterCake = new EggBatterCakeDecorator(baseBatterCake);
        baseBatterCake = new EggBatterCakeDecorator(baseBatterCake);
        baseBatterCake = new EggBatterCakeDecorator(baseBatterCake);
        System.out.println(baseBatterCake.getMsg() + ",总价：" + baseBatterCake.getPrice());
        //跟静态代理最大区别就是职责不同
        //静态代理不一定要满足is-a的关系
        //静态代理会做功能增强，同一个职责变得不一样

        //装饰器更多考虑是扩展
        //很饿，再加根香肠
        baseBatterCake = new SausageBatterCakeDecorator(baseBatterCake);
        baseBatterCake = new SausageBatterCakeDecorator(baseBatterCake);
        baseBatterCake = new SausageBatterCakeDecorator(baseBatterCake);
        baseBatterCake = new SausageBatterCakeDecorator(baseBatterCake);
        baseBatterCake = new SausageBatterCakeDecorator(baseBatterCake);
        System.out.println(baseBatterCake.getMsg() + ",总价：" + baseBatterCake.getPrice());
    }
}
