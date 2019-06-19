package com.hongdu.gupao.template.networkcourse;

/**
 * @ClassName JavaCourse
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/19 12:54
 * @Version 1.0
 */
public class JavaCourse extends NetworkCourse {

    @Override
    void checkWork() {
        System.out.println("检查Java的架构课件");
    }

    /**
     * 不需要检查作业
     * @return
     */
    @Override
    protected boolean needHomework() {
//        return false;//没有打印检查作业
        return true;
    }
}
