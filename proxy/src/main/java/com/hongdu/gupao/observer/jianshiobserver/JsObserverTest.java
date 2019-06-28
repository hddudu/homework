package com.hongdu.gupao.observer.jianshiobserver;

/**
 * @ClassName JsObserverTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/28 9:13
 * @Version 1.0
 */
public class JsObserverTest {
    public static void main(String[] args) {
        //定义一个主题 : 可观察者 : 一个嫌犯
        XianFan1 xianFan1 = new XianFan1();
        XianFan2 xianFan2 = new XianFan2();
//        xianFan1.addJsObserver(new BianYi1());
//        xianFan1.addJsObserver(new BianYi3());
//        xianFan1.addJsObserver(new BianYi2());

        BianYi1 bianYi1 = new BianYi1();
        BianYi2 bianYi2 = new BianYi2();
        BianYi3 bianYi3 = new BianYi3();

        xianFan1.addJsObserver(bianYi1);
        xianFan1.addJsObserver(bianYi2);
        xianFan1.addJsObserver(bianYi3);

        xianFan2.addJsObserver(bianYi1);
        xianFan2.addJsObserver(bianYi2);
        xianFan2.addJsObserver(bianYi3);

        //主题: message
        String message = "又卖了一批货,赚大发了!";
        String message2 = "今晚有交易,准备随时待命!";
        //通知所有的注册的观察者
        xianFan1.notice(message);
        //通知所有的注册的观察者
        xianFan2.notice(message2);


    }
}
