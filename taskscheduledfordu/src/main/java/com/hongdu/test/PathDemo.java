package com.hongdu.test;

import org.junit.Test;

/**
 * @ClassName PathDemo
 * @Description 打印各种路径
 * @Author dudu
 * @Date 2019/6/26 16:42
 * @Version 1.0
 */
public class PathDemo {

    @Test
    public void test() {
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/taskscheduledfordu/target/classes/
        System.out.println(PathDemo.class.getResource("/"));
    }
}
