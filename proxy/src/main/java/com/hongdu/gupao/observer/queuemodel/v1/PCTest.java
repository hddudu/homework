package com.hongdu.gupao.observer.queuemodel.v1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName PCTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 11:54
 * @Version 1.0
 */
public class PCTest {
    public static void main(String[] args) {
//        BlockingQueue blockingQueue = new ArrayBlockingQueue(5);
//        BlockingQueue blockingQueue = new LinkedBlockingQueue(1);
        BlockingQueue blockingQueue = new LinkedBlockingQueue(2);
        System.out.println("容器大小： " + blockingQueue.size());
        //共享了一个成员变量
        Thread pro = new Thread(new ProducerQueue(blockingQueue));
        Thread con = new Thread(new ConsumerQueue(blockingQueue));

        //线程处于 就绪状态， 抢到了cpu， 就会调用到run方法
        pro.start();
        con.start();

    }
}
