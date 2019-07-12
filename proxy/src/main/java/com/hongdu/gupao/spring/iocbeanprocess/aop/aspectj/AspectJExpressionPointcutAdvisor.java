package com.hongdu.gupao.spring.iocbeanprocess.aop.aspectj;

import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.Pointcut;
import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @ClassName AspectJExpressionPointcutAdvisor
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/12 15:43
 * @Version 1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private Advice advice;

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    /**
     * 设置表达式
     * @param expression
     */
    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
