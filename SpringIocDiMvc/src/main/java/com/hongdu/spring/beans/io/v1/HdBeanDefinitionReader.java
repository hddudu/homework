package com.hongdu.spring.beans.io.v1;

import com.hongdu.spring.beans.config.v1.HdBeanDefinition;
import com.hongdu.spring.utils.ScanPackageUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HdBeanDefinitionReader
 * @Description 资源文件读取器:
 *      走完后， 有两个容器 ：
 *              一个是 注册的类路径容器 ： 类路径信息的容器 ==》
 *              一个是 对象容器 ： 类对象信息的容器 ==》
 * @Author dudu
 * @Date 2019/7/16 16:15
 * @Version 1.0
 */
public class HdBeanDefinitionReader extends HdAbstractResourceLoader {

    /**
     * 初始化了 父类的参数  子类也又拥有了
     * @param locations
     */
    public HdBeanDefinitionReader(String... locations) {
        super(locations);
        doLoadProperties();
        doScanner((String)super.config.get(SCAN_PACKAGE));
    }

    public List<HdBeanDefinition> loadBeanDefinition3() {
        if (super.registryBeanClasses.isEmpty()) {
            return null;
        }

        List<HdBeanDefinition> result = new ArrayList<HdBeanDefinition>();
        try {
            for (String beanClassName : registryBeanClasses) {
                Class<?> beanClass = Class.forName(beanClassName);
                if(beanClass.isInterface()) {continue;}
                //beanName有三种情况:
                //1、默认是类名首字母小写
                //2、自定义名字
                //3、接口注入
                String factoryBeanName = beanClass.newInstance().getClass().getSimpleName();
                String beanClassName2 = beanClass.newInstance().getClass().getName();
                HdBeanDefinition definition = new HdBeanDefinition();
                definition.setFactoryBeanName(factoryBeanName);
                definition.setBeanClassName(beanClassName2);
//                System.out.println("名字是否一样： " + (beanClassName.equals(beanClassName2)));
                result.add(definition);
                //如果没有将接口添加到 内存中去的话， 那么注入将会是一个失败的过程
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字 : 接口名 + 类名
                    HdBeanDefinition beanDefinitionsss = new HdBeanDefinition();
                    //具体的beanClassName 还是 类的名字 ： 类的路径
                    beanDefinitionsss.setBeanClassName(beanClass.getName());
                    //factoryBeanName 是 ： 接口的类名
                    beanDefinitionsss.setFactoryBeanName(i.getName());
                    result.add(beanDefinitionsss);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //jiekou 是跳过了 但是注册了父类接口
    public List<HdBeanDefinition> loadBeanDefinitions2() {
        if (super.registryBeanClasses.isEmpty()) {
            return null;
        }
        List<HdBeanDefinition> result = new ArrayList<HdBeanDefinition>();
        for (String className : registryBeanClasses) {
            HdBeanDefinition definition = doCreateBeDefnition2(className);
            if(definition == null) {
                continue;
            }
            result.add(definition);
        }
        return result;
    }

    private HdBeanDefinition doCreateBeDefnition2(String className) {
        try {
            Class<?> beanClass = Class.forName(className);
            //有可能是一个接口
            if(!beanClass.isInterface() && !Modifier.isAbstract(beanClass.getModifiers())) {
                HdBeanDefinition definition = new HdBeanDefinition();
                definition.setBeanClassName(className);
                //不能用小写进行 设置beanName ： 不然后面可能会找不到
//                definition.setFactoryBeanName(lowerFirst(beanClass.getSimpleName()));
                definition.setFactoryBeanName(beanClass.getSimpleName());
                return definition;
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 将扫描的 beanClass 都转换成一个个的 HdBeanDefinition对象
     * @return
     */
    public List<HdBeanDefinition> loadBeanDefinitions() {
        if (super.registryBeanClasses.isEmpty()) {
            return null;
        }
        List<HdBeanDefinition> result = new ArrayList<HdBeanDefinition>();
        try {
            for (String beanClassName : registryBeanClasses) {
                Class<?> clazz = Class.forName(beanClassName);
                //如果是接口： 跳过 下一个类 : 抽象类
                if(clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {continue;}
//                 实例化对象失败
                Object instance = clazz.newInstance();
                //获取 factoryBeanName  + beanClassName(beanClassName)
                //首字母第一个小写
                String factoryBeanName = lowerFirst(clazz.getSimpleName());
                HdBeanDefinition definition = doCreateHdBeanDefintion(beanClassName, factoryBeanName);
                result.add(definition);
                //注入接口
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> i : interfaces) {
                    //factoryBeanName ： 不变 ；
                    //beanClassName 是变化的 ： 接口 + 实现类
                    // 都用了路径名： 解决了后面同名的 factoryBeanName 问题
                    definition = doCreateHdBeanDefintion(i.getName(), clazz.getName());
                    result.add(definition);
                }
            }
        } catch (Exception e) {
            System.out.println("实例化bean失败。。。。。。。。。。。。。。");
        }
        return result;
    }

    /**
     * 根据 ： 类路径名 + beanName 生成一个 bean的元数据定义对象
     * @param beanClassName
     * @param factoryBeanName
     * @return
     */
    private HdBeanDefinition doCreateHdBeanDefintion(String beanClassName, String factoryBeanName) {
        HdBeanDefinition beanDefiniton = new HdBeanDefinition();
        beanDefiniton.setFactoryBeanName(factoryBeanName);
        beanDefiniton.setBeanClassName(beanClassName);
        return beanDefiniton;
    }

    /**
     * 扫描类路径下的 包名 到 --》 registryBeanClasses ： 将要注册的 BeanClass
     * @param o
     */
    private void doScanner(String o) {
        URL url = ScanPackageUtils.getInstance().getUrl(o);
        File file = new File(url.getFile());
        File[] files = file.listFiles();
        //getName : 获取文件夹名字 + 获取文件名
        for (File fi : files) {
            //是文件夹 了怎么还有 .class 呢？？？ 可以去掉 TODO:
            if(fi.isDirectory()) {
                doScanner(o + "." + fi.getName().replaceAll(".class", ""));
            } else {
                if(!fi.getName().endsWith(".class")) {continue;}
                // com.hongdu.spring.demo.action.class
                String beanClassName = (o + "." + fi.getName()).replaceAll(".class","");
                //正式 添加 要注入的 beanClss
                super.registryBeanClasses.add(beanClassName);
            }
        }
    }

    /**
     * 加载资源文件到 properties对象中
     */
    private void doLoadProperties() {
        InputStream inputStream = super.getInputStream();
        try {
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //如果类名本身是小写字母，确实会出问题
    //但是我要说明的是：这个方法是我自己用，private的
    //传值也是自己传，类也都遵循了驼峰命名法
    //默认传入的值，存在首字母小写的情况，也不可能出现非字母的情况

    //为了简化程序逻辑，就不做其他判断了，大家了解就OK
    //其实用写注释的时间都能够把逻辑写完了
    private String lowerFirst(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
