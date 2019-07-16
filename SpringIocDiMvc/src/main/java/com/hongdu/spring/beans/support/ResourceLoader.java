package com.hongdu.spring.beans.support;

import org.springframework.util.ResourceUtils;

/**
 * @ClassName ResourceLoader
 * @Description 资源加载器
 * @Author dudu
 * @Date 2019/7/16 15:51
 * @Version 1.0
 */
public interface ResourceLoader {

    /**
     * 定义 加载配置文件路径 ：
     */
    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

}
