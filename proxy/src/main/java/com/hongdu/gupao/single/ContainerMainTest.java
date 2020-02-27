package com.hongdu.gupao.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ContainerMainTest
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/27 16:03
 * @Version 1.0
 */
public class ContainerMainTest {

    public static void main(String[] args) {
//        Thread t1 = new Thread(new TestContainer1());
//        Thread t2 = new Thread(new TestContainer2());
//
//        t1.start();
//        t2.start();
//        System.out.println("end!");

        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0; i<1000; i++){
            executorService.execute(()->{
                Object object = ContainerSingleton.getInstance("java.lang.Object");
                System.out.println(Thread.currentThread().getName() + ":" + object);
            });
        }
        executorService.shutdown();
    }
}
