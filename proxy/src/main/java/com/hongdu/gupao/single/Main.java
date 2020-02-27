package com.hongdu.gupao.single;

/**
 * @ClassName Main
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 20:59
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Test1());
        Thread t2 = new Thread(new Test2());

        t1.start();
        t2.start();
        System.out.println("end");

    }
}
