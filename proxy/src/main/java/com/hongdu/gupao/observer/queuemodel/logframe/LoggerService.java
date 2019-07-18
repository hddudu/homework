package com.hongdu.gupao.observer.queuemodel.logframe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName LoggerService
 * @Description 基于 生产者和消费者 --》 日志记录
 *          一个日志对象 从阻塞队列中取出数据，通过写日志的对象 写入日志
 *          简单的框架，功能未实现---------------------------------------------------->待添加实现
 *
 * @Author dudu
 * @Date 2019/7/18 11:13
 * @Version 1.0
 */
public class LoggerService {

    //1: 数据结构 ： 阻塞队列
    //2： 写日志对象
    //3： 内部类对象 ： 一个日志线程对象
    //      3.1: 一个线程的基本管理 ： 启动/停止
    //4： 初始化数据：
    //  4.1： 队列长度
    //  4.2:  行数？？？


    private final BlockingQueue<String> queue;

    private final int SIZE = 100;

    /**
     * 线程的基本数据
     *
     */
    private boolean isShutdown;
    private int reservations;
    private final LoggerThread loggerThread;

    /**
     * 写日志参数
     */
    private PrintWriter writer;

    /**
     * 这种提供一个无参 构造更方便外部调用
     */
    public LoggerService() throws FileNotFoundException {
        this.queue = new ArrayBlockingQueue<String>(SIZE);
        this.loggerThread = new LoggerThread();
        writer = new PrintWriter(new File(""));
    }

    /**
     * 向日志中写入信息
     * @param msg
     * @throws Exception
     */
    public void log(String msg) throws Exception {
        //首先同步写入行数， 再往队列中put数据
        synchronized (this) {
            if(isShutdown) {
                throw new IllegalArgumentException("");
            }
            ++reservations;
        }
        /**
         *  源码实现中， 如果队列满了， 会进行等待阻塞 ： await
         *   while (count == items.length)
         *                 notFull.await();
         *    当调用condition.await()方法后会使得当前获取lock的线程进入到等待队列，如果该线程能够从await()方法返回的话一定是该线程获取了与condition相关联的lock
         */
        queue.put(msg);
    }

    /**
     * 启动消费者线程
     */
    public void start() {
        loggerThread.start();
    }

    /**
     * 停止线程
     */
    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    /**
     * 日志线程
     */
    private class LoggerThread extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (LoggerService.this) {
                    if(isShutdown && reservations == 0) {
                        break;
                    }
                    String msg;
                    try {
                        /**
                         *   while (count == 0)
                         *                 notEmpty.await();
                         *     ArrayBlockingQueue中的实现是这样的， 所以会阻塞
                         */
                        msg = queue.take();
                        synchronized (LoggerService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if(writer != null) {
                            writer.close();
                        }
                    }
                }
            }
        }
    }
}
