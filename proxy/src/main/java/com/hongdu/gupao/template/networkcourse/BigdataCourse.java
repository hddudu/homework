package com.hongdu.gupao.template.networkcourse;

/**
 * @ClassName JavaCourse
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/19 12:54
 * @Version 1.0
 */
public class BigdataCourse extends NetworkCourse {

    private boolean needHomework = false;

    public BigdataCourse(boolean needHomework) {
        this.needHomework = needHomework;
    }

    @Override
    void checkWork() {
        System.out.println("检查J大数据的课后作业");
    }

    /**
     * 不需要检查作业
     * @return
     */
    @Override
    protected boolean needHomework() {
        return this.needHomework;
    }
}
