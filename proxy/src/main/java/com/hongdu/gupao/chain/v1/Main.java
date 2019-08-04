package com.hongdu.gupao.chain.v1;

/**
 * @ClassName Main
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 15:17
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        AbstractLogger logger = ChainPatternDemo.getChainOfLoggers();
        logger.logMessage(1,"一级日志记录");
        System.out.println("--------------------------------");
        logger.logMessage(2,"二级日志记录");
        System.out.println("--------------------------------");
        logger.logMessage(3,"三级日志记录");
    }
}
