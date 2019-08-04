package com.hongdu.gupao.chain.v1;

/**
 * @ClassName FileLogger
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 15:15
 * @Version 1.0
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {

    }
}
