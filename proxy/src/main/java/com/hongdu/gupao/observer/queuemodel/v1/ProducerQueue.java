package com.hongdu.gupao.observer.queuemodel.v1;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName ProducerQueue
 * @Description 生产者
 * @Author dudu
 * @Date 2019/7/18 11:50
 * @Version 1.0
 */
public class ProducerQueue implements Runnable {

    private final BlockingQueue proQueue;

    public ProducerQueue(BlockingQueue proQueue) {
        this.proQueue = proQueue;
    }

    @Override
    public void run() {
        //生产产品到 队列容器中
       try {
           for (int i = 0; i < 10; i++) {
               //生产苹果
               System.out.println("生产者生产的苹果编号为 ： " + i);
               //这是一个阻塞的方法， 如果满了 会自动阻塞
               proQueue.put(i);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
