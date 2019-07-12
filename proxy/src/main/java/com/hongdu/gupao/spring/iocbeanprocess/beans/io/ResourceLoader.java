package com.hongdu.gupao.spring.iocbeanprocess.beans.io;

import com.hongdu.hdutil.HdJavaEveUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * @ClassName ReourceLoader
 * @Description 资源加载器
 * @Author dudu
 * @Date 2019/7/11 19:03
 * @Version 1.0
 */
public class ResourceLoader {

    public Resources getResources(String location) {
        //获取资源文件
        URL resources = this.getClass().getClassLoader().getResource(location);
        return new URLResources(resources);
    }

    @Test
    public void test() throws IOException {
        String location = "application.properties";
        Resources resources = getResources(location);
        String s = HdJavaEveUtils.getInstance().readInputStream2String(resources.getInputStream());
        System.out.println(s);
    }

}
