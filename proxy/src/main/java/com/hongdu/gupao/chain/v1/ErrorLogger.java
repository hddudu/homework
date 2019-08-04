package com.hongdu.gupao.chain.v1;

/**
 * @ClassName ErrorLogger
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 15:15
 * @Version 1.0
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
