package com.hongdu.gupao.single;

/**
 * @ClassName ContainerExecutorRunHandler
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/27 17:15
 * @Version 1.0
 */
public class ContainerExecutorRunHandler implements RunHandler
{
    @Override
    public void handler() {
        Object containerSingleton = ContainerSingleton
                .getInstance("com.hongdu.gupao.single.ClassNameClass");
        System.out.println(Thread.currentThread().getName() + " : " + containerSingleton);
    }
}
