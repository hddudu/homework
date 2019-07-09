package com.hongdu.gupao.spring.implinterface;

/**
 * @ClassName Test
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/8 9:42
 * @Version 1.0
 */
public class Test {
    @org.junit.Test
    public void tets() {
        AnnotationInterfaceImpl3 a = new AnnotationInterfaceImpl3();
        //获取所有实现的接口
        Class<?>[] clazzs = a.getClass().getInterfaces();
        for (Class<?> clazz : clazzs) {
            System.out.println(clazz.getClassLoader());
            System.out.println("clazz.getInterfaces() :::::" + clazz.getInterfaces().length);
            System.out.println("继承的接口： :::::" + clazz.getInterfaces());
            System.out.println("类的路径名： " + clazz.getName());
            System.out.println("简单的类名： " + clazz.getSimpleName());
            System.out.println("父类 ： " + clazz.getSuperclass());
            System.out.println("类的包名： " + clazz.getPackage());
            System.out.println("类的所有字段 ： 数组类型 ： " + clazz.getDeclaredFields());
            System.out.println("类的类型名： " + clazz.getTypeName());
            System.out.println("类的Signers ： " + clazz.getSigners());
            System.out.println("类的声明构造器 数组集合： " + clazz.getDeclaredConstructors());
            System.out.println("类的声明的类 ： " + clazz.getDeclaredClasses());
            System.out.println("类的绝对路径 ： " + clazz.getCanonicalName());
            System.out.println("类的类型参数 数组类型： " + clazz.getTypeParameters());
            System.out.println("类的修饰符号 ： " + clazz.getModifiers());
            System.out.println("类的资源： " + clazz.getResource(""));
            if(clazz.getInterfaces().length > 0) {
                System.out.println("遍历第二层的接口--------------------------------------------------");
                Class<?>[] classes = clazz.getInterfaces();
               for (Class<?> c : classes) {
                   System.out.println("类的路径名： " + c.getName());
                   System.out.println("简单的类名： " + c.getSimpleName());
               }
                System.out.println("遍历第二层的接口--------------------------------------------------");
            }
         }
    }
}
