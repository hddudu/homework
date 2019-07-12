package com.hongdu.gupao.spring.iocbeanprocess.beans.factory;

import com.hongdu.gupao.spring.iocbeanprocess.BeanReference;
import com.hongdu.gupao.spring.iocbeanprocess.aop.BeanFactoryAware;
import com.hongdu.gupao.spring.iocbeanprocess.beans.BeanDefinition;
import com.hongdu.gupao.spring.iocbeanprocess.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName AutowireCapableBeanFactory
 * @Description 可自动装配内容的BeanFactory
 *      自动装配的关键点是 ：
 *          1： 设置属性 ： 其实就是依赖的注入 ： 依赖的设置值
 * @Author dudu
 * @Date 2019/7/12 9:51
 * @Version 1.0
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     *  为 BeanDefinition的 实例对象 bean 设置属性值
     *   实现了 抽象beanFactory的依赖注入方法
     * @param bean
     * @param beanDefinition
     */
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

        if(bean instanceof BeanFactoryAware) {
            // bean的增强 Aware 接口
         ((BeanFactoryAware)bean).setBeanFactory(this);

        }
        //遍历 已经读取到的PropertyValues属性 ， 进行自动装配 自动注入
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            //获取属性值
            Object value = propertyValue.getValue();
            //如果是 ref 引用的 待注入的属性
            if(value instanceof BeanReference) {
                BeanReference reference = (BeanReference) value;
                //getBean ： 其实就是 创建 ： 初始化 ： 实例化 ： 单例化的过程
                value = getBean(reference.getName());

            }
            //实例化完成了 : 依赖属性完成
            //反射设置到原装类中
            try {
                //bean.getClass() : 表示 ： 类实例对象
                //获取方法名称
                //获取参数类型 : 其实就是 property标签的value的类型
                //寻找到了要设置属性的setter方法
                Method setMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0,1).toUpperCase()
                                        + propertyValue.getName().substring(1),
                                            value.getClass());
                //设置方法是可以访问的
                setMethod.setAccessible(true);
                //反射调用 ： 为类实例中的 ： set方法设置属性值 ： 其实就是依赖注入
                setMethod.invoke(bean, value);
            } catch (Exception e){
                //如果出现异常， 那么就进行字段的设置值 ： 异常的情况是 ： 没有set方法
                Field field = null;
                field = bean.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(bean, value);
                //继续处理下一个依赖属性
                continue;
            }
        }
    }
}
