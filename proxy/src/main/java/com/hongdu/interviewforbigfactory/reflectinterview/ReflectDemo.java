package com.hongdu.interviewforbigfactory.reflectinterview;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *      https://www.cnblogs.com/lakeslove/p/5978382.html JVM基础
 *      https://www.jianshu.com/p/f67182a482eb 反射基础
 *
 *  Java反射机制主要提供下面几种用途：
 *
 * 在运行时判断任意一个对象所属的类
 * 在运行时构造任意一个类的对象
 * 在运行时判断任意一个类所具有的成员变量和方法
 * 在运行时调用任意一个对象的方法
 *
 * java 反射复习： java.lang.reflect
 * 1： 类加载
 * 2： 类连接
 * 3： 类初始化
 * 4： 类加载器以及实现机制
 * 5： 继承ClassLoader实现自定义类加载器
 * 6： 使用URLClassLoader
 * 7： 使用Class对象
 * 8：  动态创建java对象
 * 9： 动态调用方法
 * 10： 访问并修改java对象的属性值
 * 11： 使用反射操作数组
 * 12： 使用Proxy和InvocationHandler 创建动态代理
 * 13： AOP入门
 * 14： Class类的泛型
 * 15： 通过反射获取泛型类型
 *      java.lang.reflect包下的接口和类
 *
 *      同一个JVM进程的所有线程、 所有变量都处于同一个进程里， 它们都是用JVM进程的内存区。
 *      1： 程序运行到最后
 *      2： System.exit() Runtime.getRuntime().exit();
 *      3: 程序运行过程中遇到未捕获的异常或错误而结束
 *      4： 程序在平台强制结束了JVM进程。
 *
 *      当程序使用一个类的时候，如果未被加载==》加载，连接，初始化来对该类进行初始化
 *        一： 加载： 将类的class文件读入内存，并为之创建一个java.lang.Class对象，
 *              当程序中使用任何类时,系统都会为之建立一个java.lang.Class对象.(几乎所有的类都是java.lang.Class的实例);
 *
 *              加载方式： ①类加载器 ： ClassLoader 《--由JVM提供，==》名字叫做： 系统类加载器
 *                  此外： 开发者可以通过继承ClassLoader基类类创建自己的类加载器；
 *                  来源
 *                  ①从本地文件加载class文件
 *                  Ⅱ从jar包加载class文件
 *                  🌂通过网络加载class文件
 *                  ④把一个java源文件动态编译，并执行加载==》jdk动态代理或者cglib的动态代理
 *                  类加载器无须等到首次使用才进行加载，Java虚拟机规范允许预先加载某些类；
 *        二：类的连接
 *          类加载之后，系统为之生成一个对应Class对象，接着将进入连接阶段，连接阶段负责将类的二进制数据合并到jre中，
 *          ①验证：检验加载的类是否有正确的内部结构
 *          Ⅱ准备：为类变量分配内存，并设置默认初始值
 *          🌂解析：将类的二进制数据的符号引用替换成直接引用；
 *              符号引用和直接引用有什么差别？？？？
 *        三：类的初始化
 */
public class ReflectDemo {

    /**
     * 类是java.lang.Class类的实例对象，而Class是所有类的类（There is a class named Class）
     * 可以看到构造器是私有的，只有JVM可以创建Class的对象，因此不可以像普通类一样new一个Class对象，
     * 虽然我们不能new一个Class对象，但是却可以通过已有的类得到一个Class对象，共有三种方式，如下:
     * Class c1 = Code.class;
     * 这说明任何一个类都有一个隐含的静态成员变量class，这种方式是通过获取类的静态成员变量class得到的
     * Class c2 = code1.getClass();
     * code1是Code的一个对象，这种方式是通过一个类的对象的getClass()方法获得的
     * Class c3 = Class.forName("com.trigl.reflect.Code");
     * 这种方法是Class类调用forName方法，通过一个类的全量限定名获得
     *
     *  获取成员变量 field
     *  获取类方法
     *  获取构造器
     *  newInstance 构造实例
     *  Class.forName();
     * ①直接 .class 获取类对象
     * Ⅱ通过类的全量限定名
     * 🌂通过 getClass方法 ： 在Object中
     * ④类类型 ： 就是类的类型，也就是描述一个类是什么，都有哪些东西，所以我们可以通过类类型知道一个类的属性和方法，
     *  并且可以调用一个类的属性和方法，这就是反射的基础。
     *  类类型： int short long double char String float byte boolean
     *  Person
     * @param args
     */
    public static void main(String[] args) {

//        public Method getDeclaredMethod(String name, Class<?>... parameterTypes) // 得到该类所有的方法，不包括父类的
//        public Method getMethod(String name, Class<?>... parameterTypes) // 得到该类所有的public方法，包括父类的
        Class clazz = ReflectDemo.class;
        System.out.println(clazz);//class com.hongdu.interviewforbigfactory.reflectinterview.ReflectDemo

        A a = new A();
        System.out.println(a.getClass());
        try {
            Class clazz2 = Class.forName("com.hongdu.interviewforbigfactory.reflectinterview.A");
            Object o = clazz2.newInstance();
            Method method = clazz2.getMethod("fun", String.class, int.class);//方法名 + 参数类型
            method.invoke(o, "tengji",20);//第一个为实例对象， 后面为具体参数值
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        怎样，是不是感觉很厉害，我们只要知道这个类的路径全称就能玩弄它于鼓掌之间。
        /**
         * Class c = Class.forName("com.tengj.reflect.Person");
         * Method[] methods = c.getDeclaredMethods(); // 得到该类所有的方法，不包括父类的
         * 或者：
         * Method[] methods = c.getMethods();// 得到该类所有的public方法，包括父类的
         *
         *
         */
    }
}
