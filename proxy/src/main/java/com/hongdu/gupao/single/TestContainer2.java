package com.hongdu.gupao.single;

/**
 * @ClassName TestContainer2
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/27 16:01
 * @Version 1.0
 */
public class TestContainer2 implements Runnable {

    @Override
    public void run() {
        Object containerSingleton = ContainerSingleton
                .getInstance("com.hongdu.gupao.single.ClassNameClass");
        System.out.println(Thread.currentThread().getName() + " : " + containerSingleton);
    }
}
