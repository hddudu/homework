package com.hongdu.gupao.factory.pay.simple;

/**
 * @ClassName Main
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/24 16:16
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        PayFactory payFactory = new PayFactory();
        IPay iPay = payFactory.getPay("ali");
        iPay.pay();
        IPay iPay2 = payFactory.getPay("wechat");
        iPay2.pay();
        IPay iPay3 = payFactory.getPay("union");
        iPay3.pay();

        //Exception in thread "main" java.lang.NullPointerException
        //	at com.hongdu.gupao.factory.pay.simple.Main.main(Main.java:22)
//        IPay iPay4 = payFactory.getPay("union2");
//        iPay4.pay();

        IPay iPay5 = payFactory.getPay("crossborder");
        iPay5.pay();
    }
}
