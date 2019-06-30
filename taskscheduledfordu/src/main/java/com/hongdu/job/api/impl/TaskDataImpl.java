package com.hongdu.job.api.impl;

import com.hongdu.job.api.TaskData;

/**
 * @ClassName TaskDataImpl
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/29 20:54
 * @Version 1.0
 */
public class TaskDataImpl implements TaskData {

    @Override
    public void execute() {
        System.out.println("定时任务。。。。。。。。。。。。执行中。。。。。");
    }
}
