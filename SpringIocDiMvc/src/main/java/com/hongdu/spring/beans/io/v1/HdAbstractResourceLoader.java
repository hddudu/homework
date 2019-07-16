package com.hongdu.spring.beans.io.v1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName HdAbstractResourceLoader
 * @Description 把参数都放在了上层， 下层专注了逻辑处理 : 比较好了封装了
 * @Author dudu
 * @Date 2019/7/16 16:14
 * @Version 1.0
 */
public abstract class HdAbstractResourceLoader implements HdResourceLoader {

    /**
     * 需要注册的  bean
     */
    protected List<String> registryBeanClasses;

    protected Properties config;

    /**
     * 保护的包名 ： 放哪里都可以 因为是 final
     */
    protected final String SCAN_PACKAGE = "scanPackage";

    protected final String[] configLocations;

    public HdAbstractResourceLoader(String...locations) {
        this.configLocations = locations;
        this.registryBeanClasses = new ArrayList<>(32);
        this.config = new Properties();
    }

    @Override
    public InputStream getInputStream(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replaceAll("classpath:", ""));
        return inputStream;
    }
}
