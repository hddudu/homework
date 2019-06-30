package com.hongdu.job;

import com.hongdu.job.api.impl.TaskDataImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName JobTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/29 21:10
 * @Version 1.0
 */
public class JobTest {

    public static void main(String[] args) {
        /**
         * 加载配置文件
         */
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("quartz.xml");
        QuartzManager quartzManager = (QuartzManager) ctx.getBean("quartzManager");
        try {
            System.out.println("【系统启动】开始(每1秒输出一次 job2)...");
            Thread.sleep(5000);
            System.out.println("【增加job1启动】开始(每1秒输出一次)...");
            quartzManager.addJob("test", "test", "test", "test", QuartzTask.class, "0/1 * * * * ?",new TaskDataImpl());

            Thread.sleep(5000);
            System.out.println("【修改job1时间】开始(每2秒输出一次)...");
            quartzManager.modifyJobTime("test", "test", "test", "test", "0/2 * * * * ?");
            Thread.sleep(10000);
            System.out.println("【移除job1定时】开始...");
            quartzManager.removeJob("test", "test", "test", "test");

            // 关掉任务调度容器
            quartzManager.shutdownJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
