package com.hongdu.job;

import org.quartz.Scheduler;

/**
 * @ClassName QuartzManager
 * @Description  定时任务管理器 : 定时任务配置管理中心
 * @Author dudu
 * @Date 2019/6/26 16:21
 * @Version 1.0
 */
public class QuartzManager {

    private Scheduler scheduler;

    public void addJob() {}

    public void removeJob() {}

    public void modifyJobTime() {}

    /**
     * 启动所有定时任务
     */
    public void startJobs() {

    }

    public void shutdownJobs() {}

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
