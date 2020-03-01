//package com.hongdu.gupao.proxy.staticproxy.girlsproxy;
//
///**
// * @ClassName UpcastingDemo
// * @Description TODO
// * @Author dudu
// * @Date 2020/3/1 21:58
// * @Version 1.0
// */
//public class UpcastingDemo {
//    public static void show(Student2 s) {
//        s.eat(Value.v1);
//    }
//
//    public static void show(Teacher t) {
//        t.eat(Value.v1);
//    }
//
//    public static void show(Person p) {
//        p.eat(Value.v1);
//    }
//
//    public static void main(String[] args) {
//        Student2 s = new Student2();
//        Teacher t = new Teacher();
//        Person p = new Person();
//        show(s);
//        show(t);
//        show(p);
//    }
//}
//
//class Value {
//    private int count = 1;
//
//    private Value(int count) {
//        this.count = count;
//    }
//
//    public static final Value
//            v1 = new Value(1),
//            v2 = new Value(2),
//            v3 = new Value(3);
//}
//
//class Person {
//
//    public void eat(Value v) {
//        System.out.println("Person.eat()");
//    }
//}
//
//class Teacher extends Person {
//    public void eat(Value v) {
//        System.out.println("Teacher.eat()");
//    }
//}
//
//class Student2 extends Person {
//    public void eat(Value v) {
//        System.out.println("Student.eat()");
//    }
//}