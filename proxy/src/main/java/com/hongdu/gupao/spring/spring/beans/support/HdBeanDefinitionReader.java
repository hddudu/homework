package com.hongdu.gupao.spring.spring.beans.support;


import com.hongdu.gupao.spring.spring.beans.config.HdBeanDefiniton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName HdBeanDefinitionReader
 * @Description 加载 loadBeanDefinitions
 * @Author dudu
 * @Date 2019/7/14 21:59
 * @Version 1.0
 */
public class HdBeanDefinitionReader {

    /**
     * 需要注册的  bean
     */
    private List<String> registryBeanClasses = new ArrayList<>();

    private Properties config = new Properties();

    /**
     * 固定 配置文件中的KEY ； 相当于xml中的规范
     */
    private final String SCAN_PACKAGE = "scanPackage";

    /**
     * 可变参数 ： 0或者其他
     * @param locations
     */
    public HdBeanDefinitionReader(String...locations) {
        //通过URL 定位找到其所对应的文件
        //通过web.xml加载进行来的话
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replaceAll("classpath:", ""));
        //本地测试采用这个形式获取 application.properties文件
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(locations[0]);
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

        doScanner(config.getProperty(SCAN_PACKAGE));
        /**
         * com.hongdu.gupao.spring.demo.DemoIntroduction,
         * com.hongdu.gupao.spring.demo.mvc.DemoAction,
         * com.hongdu.gupao.spring.demo.mvc.TwoAction,
         * com.hongdu.gupao.spring.demo.service.IDemoService,
         * com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl,
         */
    }

    private void doScanner(String scanPackage) {
        URL packageFiles = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        File files = new File(packageFiles.getFile());
        //遍历文件夹
        for (File file : files.listFiles()) {
            //是否是文件夹
            if(file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if(!file.getName().endsWith(".class")) {
                    continue;
                } else {
                    //file.getName 只是文件名而已 : 需要的是包路径
                    String clazzName = scanPackage + "." + file.getName().replaceAll(".class", "");
                    registryBeanClasses.add(clazzName);
                }
            }
        }
    }

    public static void main(String[] args) {
        HdBeanDefinitionReader reader = new HdBeanDefinitionReader("application.properties");
        //遍历 扫描的类
        if(!reader.registryBeanClasses.isEmpty()) {
            for (String s : reader.registryBeanClasses) {
                System.out.println(s + ",");
            }
        }
        System.out.println("扫描完包结束---------------------------------------");
        System.out.println("加载及 映射到 内存容器中 结束---------------------------------------");
        List<HdBeanDefiniton> result = reader.loadBeanDefinitions();
        for (HdBeanDefiniton beanDefiniton : result) {
            System.out.println(beanDefiniton.toString());
        }
    }


    /**
     * 获取 Properties
     * @return
     */
    public Properties getConfig() {
        return this.config;
    }

    /**
     * 可变参数 ： 0或者其他
     *  把配置文件中扫描到的 s所有的配置信息转换为 一个 HdBeanDefinition对象 以方便后面的IOC操作
     * @param locations
     * @return
     */
    public List<HdBeanDefiniton> loadBeanDefinitions(String...locations) {
        List<HdBeanDefiniton> result = new ArrayList<HdBeanDefiniton>();
//        第一个版本
//        for (String className : registryBeanClasses) {
//            HdBeanDefiniton beanDefiniton = doCreateBeanDefinition(className);
//            if(null == beanDefiniton) {continue;}
//            result.add(beanDefiniton);
//        }
        //        第二个版本
        try {
            for (String className : registryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，是不能实例化的
                //用它实现类来实例化
                if(beanClass.isInterface()) { continue; }
                //beanName有三种情况:
                //1、默认是类名首字母小写
                //2、自定义名字
                //3、接口注入
                String factoryBeanName = beanClass.newInstance().getClass().getSimpleName();
                String beanClassName = beanClass.newInstance().getClass().getName();
                result.add(doCreateBeanDefinition(factoryBeanName, beanClassName));
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字 : 接口名 + 类名
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        /**
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.DemoIntroduction, lazyInit=false, factoryBeanName=demoIntroduction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.mvc.DemoAction, lazyInit=false, factoryBeanName=demoAction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.mvc.TwoAction, lazyInit=false, factoryBeanName=twoAction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl, lazyInit=false, factoryBeanName=demoServiceImpl)
********** 缺少接口 ： 修改后 ： 接口也注入进来了 ： 但是接口杜英的实现类路径 ： 仍然是类
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.DemoIntroduction, lazyInit=false, factoryBeanName=DemoIntroduction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.mvc.DemoAction, lazyInit=false, factoryBeanName=DemoAction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.mvc.TwoAction, lazyInit=false, factoryBeanName=TwoAction)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl, lazyInit=false, factoryBeanName=DemoServiceImpl)
         * HdBeanDefiniton(beanClassName=com.hongdu.gupao.spring.demo.service.impl.DemoServiceImpl, lazyInit=false, factoryBeanName=com.hongdu.gupao.spring.demo.service.IDemoService)
         *
         */
    }

    //把每一个配信息解析成一个BeanDefinition
    private HdBeanDefiniton doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        HdBeanDefiniton beanDefiniton = new HdBeanDefiniton();
        beanDefiniton.setFactoryBeanName(factoryBeanName);
        beanDefiniton.setBeanClassName(beanClassName);
        return beanDefiniton;
    }

    //把每一个配置信息解析成一个 HdBeanDefiniton
    private HdBeanDefiniton doCreateBeanDefinition(String className) {
//        第一个版本
//        try {
//            Class<?> beanClass = Class.forName(className);
//            //有可能是一个接口 ： 接口 的话就处理实现类 ， 但是这里还是先不处理接口
//            //接口的话， 用它的实现类作为beanClassName
//            if(beanClass.isInterface()) { return null;}
//            HdBeanDefiniton beanDefiniton = new HdBeanDefiniton();
//            beanDefiniton.setBeanClassName(className);
//            // 存储的是 ： 类名 ： userService 的格式
//            beanDefiniton.setFactoryBeanName(lowerFirst(beanClass.getSimpleName()));
//            return beanDefiniton;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        return null;
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
