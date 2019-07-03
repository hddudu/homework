package com.hongdu.gupao.spring;

/**
 * @ClassName SpringIntroduction
 * @Description spring的介绍
 *      1： ioc
 *      2： id
 *      3： aop
 *      4：
 * @Author dudu
 * @Date 2019/7/3 18:29
 * @Version 1.0
 */
public class SpringIntroduction {
    public static void main(String[] args) {
        String packageName = "com.hongdu.one.one";
        System.out.println(packageName.replaceAll("\\.", "/"));
        System.out.println(SpringIntroduction.class.getClassLoader().getResource(""));
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu
        System.out.println(SpringIntroduction.class.getClassLoader().getResource("/com/hongdu"));
    }
}
