package com.hongdu.gupao.single;

/**
 * @ClassName TestExecutorTest
 * @Description 测试容器式单例的安全问题 ： 确实存在
 * @Author dudu
 * @Date 2020/2/27 17:16
 * @Version 1.0
 */
public class TestExecutorTest {
    public static void main(String[] args) {
        ConcurrentExecutor concurrentExecutor = new ConcurrentExecutor();
        try {
            concurrentExecutor.execute(new ContainerExecutorRunHandler(),
                   10000, 100 );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
