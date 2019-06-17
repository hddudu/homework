package com.hongdu.interviewforbigfactory.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.jianshu.com/p/690b82c69158 参考： 待思考？
 * @ClassName MyThreadLocal
 * @Description 通过一个同步的synchronizedMap来存储存放thread和值的 map来保证线程的同步。
 * @Author dudu
 * @Date 2019/6/17 13:52
 * @Version 1.0
 */
public class MyThreadLocal<T> {

    //内部容器 ： 线程容器 ： 保存副本
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    //初始化值
    protected T initialValue() {
        return null;
    }

    //初始化名词
    private T initialization(Thread currentThread) {
        T t = initialValue();
        container.put(currentThread, t);
        return t;
    }
    //get
    public T get() {
        Thread currentThread = Thread.currentThread();
        T t = container.get(currentThread);
        if(t != null) {
            return t;
        }
        return initialization(currentThread);
    }

    //remove
    public void remove() {
        Thread currentThread = Thread.currentThread();
        if(this.container.containsKey(currentThread)) {
            this.container.remove(currentThread);
        }
    }

    //set
    public void set(T t) {
        Thread currentThread = Thread.currentThread();
        this.container.put(currentThread, t);
    }

}
