package com.hongdu.gupao.template.networkcourse;

import sun.nio.ch.Net;

/**
 * @ClassName NetworkCourseTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/19 12:55
 * @Version 1.0
 */
public class NetworkCourseTest {

    public static void main(String[] args) {
        //抽象类指向子类呢？
        System.out.println("---Java架构师课程---");
        NetworkCourse javaCourse = new JavaCourse();
        javaCourse.createCourse();
        System.out.println("-----------------------------------------");
        System.out.println("---大数据课程---");
        NetworkCourse bigdata = new BigdataCourse(true);
        bigdata.createCourse();
    }
}
