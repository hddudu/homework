package com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface;

import org.aopalliance.aop.Advice;

/**
 * @ClassName Advisor
 * @Description 通知器
 * @Author dudu
 * @Date 2019/7/12 14:48
 * @Version 1.0
 */
public interface Advisor {

    /**
     * 获取通知器对象
     * @return
     */
    Advice getAdvice();
}
