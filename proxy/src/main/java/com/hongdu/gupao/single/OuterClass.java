package com.hongdu.gupao.single;

import java.util.Random;

/**
 * @ClassName OuterClass
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 21:51
 * @Version 1.0
 */
public class OuterClass {

    public static long OUTER_DATE = System.currentTimeMillis();
    public static int a = 1;
    //外部类代码块
    static {
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis() + " A ");
    }

    //外部类构造方法
    public OuterClass() {
        timeElapsed();
        System.out.println("外部类构造函数事件：" + System.currentTimeMillis() + " E " );
    }

    //静态内部类
    static class InnerStaticClass {
        static {
            System.out.println("内部类静态块加载时间：" + System.currentTimeMillis() + " B ");
        }
//        public static long INNER_STATIC_DATE = System.currentTimeMillis();
        public static OuterClass INNER_STATIC_DATE = new OuterClass();


    }

    // 非静态内部类
    class InnerClass {
        public long INNER_DATE = 0;
        public InnerClass() {
            timeElapsed();
            INNER_DATE = System.currentTimeMillis();
        }
    }

    public static void Hello(){System.out.println("Hello");}

    public static void main(String[] args) {

        System.out.println("内部类静态变量加载时间：" + InnerStaticClass.INNER_STATIC_DATE  + " C " );
        System.out.println("外部类静态变量加载时间：" + OuterClass.OUTER_DATE   + " D ");
    }

    //单纯的为了耗时而已
    private void timeElapsed() {
        for (int i = 0; i < 10000000; i++) {
            int a = new Random(100).nextInt(), b = new Random(100).nextInt();
            a = a + b;
        }
    }
}
