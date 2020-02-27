package com.hongdu.gupao.single;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 20:56
 * @Version 1.0
 */
public class Test1 implements Runnable {

    @Override
    public void run() {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println(Thread.currentThread().getName() + " : " + singleTon);
    }
}
