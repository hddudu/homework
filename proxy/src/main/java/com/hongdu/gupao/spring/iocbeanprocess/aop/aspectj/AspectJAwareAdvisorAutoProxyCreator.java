package com.hongdu.gupao.spring.iocbeanprocess.aop.aspectj;

import com.hongdu.gupao.spring.iocbeanprocess.aop.BeanFactoryAware;
import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanPostProcessor;
import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.AbstractBeanFactory;
import com.hongdu.gupao.spring.iocbeanprocess.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @ClassName AspectJAwareAdvisorAutoProxyCreator
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/12 16:04
 * @Version 1.0
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory)beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 后置 处理 增强
     *      那么原来的bean 已经不是原装的bean了， 它已经是被代理的bean对象了 ： 携带切面方法的 代理类 的代理对象了
     *
     *      TODO 经过测试 ： 这一段代码还是不清楚是做什么用的？？？？？？？？？？
     *      准确的说是 ： 是注释的那个部分的作用 ： 它生成了代理类， 而且是在注入之前生成的代理类 所以有问题了！
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        //自定义 切面对象 通知器
        if(bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        //cglib的代理类对象
        if(bean instanceof MethodInterceptor) {
            return bean;
        }
        /**
         * 注释： 表达式这一段
         */
//        if(beanFactory != null) {
//            List<AspectJExpressionPointcutAdvisor> advisors = beanFactory
//                    .getBeansForType(AspectJExpressionPointcutAdvisor.class);
//            for (AspectJExpressionPointcutAdvisor advisor : advisors) {
//                if(advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
//                    ProxyFactory advisedSupport = new ProxyFactory();
//                    //设置通知器
//                    advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
//                    advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
//
//                    TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
//                    advisedSupport.setTargetSource(targetSource);
//                    return advisedSupport.getProxy();
//                }
//            }
//        }
        return bean;
    }
}
