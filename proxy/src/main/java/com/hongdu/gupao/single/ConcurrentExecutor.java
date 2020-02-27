package com.hongdu.gupao.single;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName ConcurrentExecutor
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/27 17:09
 * @Version 1.0
 */
public class ConcurrentExecutor {

    /**
     * @param runHandler
     * @param executeCount 发起请求总数
     * @param concurrentCount 同时并发执行的线程数
     * @throws Exception
     */
    public static void execute(final RunHandler runHandler,int executeCount,int concurrentCount) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //控制信号量，此处用于控制并发的线程数 : 令牌数目 ： 拿到令牌才能继续任务 否则阻塞
        final Semaphore semaphore = new Semaphore(concurrentCount);
        //闭锁，可实现计数量递减
        // 计数器 ： 执行完所有的后才开始执行
        final CountDownLatch countDownLatch = new CountDownLatch(executeCount);
        for (int i = 0; i < executeCount; i ++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取许可 令牌后 继续执行业务逻辑
                        runHandler.handler();
                        //释放许可
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //减少1
                    countDownLatch.countDown();
                }
            });
        }
        //实现阻塞主线程的功能
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();

    }

}
