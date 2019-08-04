package com.hongdu.gupao.chain.v1;

/**
 * @ClassName ChainPatternDemo
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 15:16
 * @Version 1.0
 */
public class ChainPatternDemo {
    public static AbstractLogger getChainOfLoggers() {

        //3
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        //2
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        //1
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        //3-->2-->1
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return  errorLogger;
    }
}
