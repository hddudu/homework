package com.hongdu.gupao.chain.v1;

/**
 * @ClassName ConsoleLogger
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 15:14
 * @Version 1.0
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger :"+message);
    }
}
