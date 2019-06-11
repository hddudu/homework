package com.hongdu.gupao.proxy.staticproxy.girlsproxy;

import com.hongdu.gupao.proxy.Person;

import java.util.Date;

/**
 * 女孩找对象 : 通过代理平台
 *  婚介所
 *  相亲平台
 *  媒婆
 */
public class Girl implements Person {
    public Girl() {
    }

    private Integer age;
    private String name;
    private Date birthDay;

    //来个自我介绍吧
    public Girl(String name,int age) {
        this.name = name;
        this.age = age;//这个int到Integer发生了什么
    }

    @Override
    public void findLover() {
        System.out.println("i'm " + this.name + ",今年" + this.age + "岁.");
        System.out.println("高富帅是我想找的对象!暖男也可以吧!");
    }

    @Override
    public void findJob() {
        System.out.println("i'm " + this.name + ",今年" + this.age + "岁.");
        System.out.println("我擅长编程,架构,等等,我来应聘java!");
    }

    public void eat() {
        System.out.println("吃饭，好饿!");
    }

    public void sleep() {
        System.out.println("睡觉,好困!");
    }
    public void cooking() {
        System.out.println("无聊,烹饪!");
    }
    public void examing() {
        System.out.println("做题,考试!");
    }
    public void playGame() {
        System.out.println("手痒痒,打王者荣耀!");
    }


    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getBirthDay() {
        return birthDay;
    }
}
