package com.hongdu.job.api;

/**
 * @ClassName TaskData
 * @Description 任务接口，接口具体实现由调用者来实现
 * @Author dudu
 * @Date 2019/6/26 16:27
 * @Version 1.0
 */
public interface TaskData {
    /**
     * 任务具体的执行方法
     */
    void execute();
}
