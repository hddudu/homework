package com.hongdu.gupao.spring.spring.beans.support;

import java.io.InputStream;

/**
 * @ClassName ResourceLoader
 * @Description 资源加载器
 * @Author dudu
 * @Date 2019/7/15 9:26
 * @Version 1.0
 */
public interface ResourceLoader {
    InputStream getInputStream() throws Exception;
}
