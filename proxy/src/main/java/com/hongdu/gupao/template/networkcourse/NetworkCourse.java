package com.hongdu.gupao.template.networkcourse;

/**
 * @ClassName NetworkCourse
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/19 12:42
 * @Version 1.0
 */
public abstract class NetworkCourse {

    //网络课程的通用模板
    //模板流程不需要重写： 使用final修饰
    protected final void createCourse() {
        //发布预习资料
        this.publishPreResource();
        //制作ppt
        this.createPpt();
        //直播讲课
        this.liveVideo();
        //提交源码
        this.submitSource();
        //抽象方法 留在了子类实现， 所以可以添加一个额外的钩子方法，控制整个方法的执行（是否执行）
        if(needHomework()) {
            checkWork();
        }
    }

    //抽象方法
    abstract void checkWork();

    protected boolean needHomework() {
        return false;
    }

    //提交源码
    final void submitSource() {
        System.out.println("提交源码");
    }

    //直播讲课
    final void liveVideo() {
        System.out.println("直播讲课");
    }

    //制作ppt课件
    final void createPpt() {
        System.out.println("制作ppt课件");
    }

    //发布预习资料： 不给覆写
    final void publishPreResource() {
        System.out.println("发布预习资料");
    }


}
