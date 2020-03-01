package com.hongdu.gupao.proxy.staticproxy.girlsproxy;

/**
 * @ClassName Student
 * @Description 2.为什么要上溯造型
 * 为什么在调用eat是要有意忽略调用它的对象类型呢？
 * 如果让show方法简单地获取Student句柄似乎更加直观易懂，
 * 但是那样会使衍生自Person类的每一个新类都要实现专属自己的show方法：
 *
 * 如果子类中没有定义覆盖方法，则会调用父类中的方法：
 *
 * 观察结果，对于静态方法而言，不管父类引用指向的什么子类对象，调用的都是父类的方法。
 *
 * 助记口诀
 * - 静态方法:静态方法看父类
 * - 非静态方法：非静态方法看子类
 *
 * @Author dudu
 * @Date 2020/3/1 21:52
 * @Version 1.0
 */
public class Student
//        extends  Person
{

    public static void main(String[] args) {
        Student s = new Student();
//        Person.show(s);     // ①
    }
}
//
//class Person {
//    public void eat() {
//        System.out.println("eat");
//    }
//
//    static void show(Person p) {
//        p.eat();
//    }
//}