package com.hongdu.job;

import com.hongdu.job.api.TaskData;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName QuartzTask
 * @Description 小队定时任务
 * @Author dudu
 * @Date 2019/6/26 16:25
 * @Version 1.0
 */
public class QuartzTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //执行任务
        JobDataMap jobMap = jobExecutionContext.getJobDetail().getJobDataMap();
        TaskData quartzTask = (TaskData)jobMap.get("task");
        //调用接口参数
        quartzTask.execute();
    }
}
