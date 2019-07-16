package com.hongdu.spring.beans.io.v1;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName HdResourceLoader
 * @Description 获取流接口
 * @Author dudu
 * @Date 2019/7/16 16:12
 * @Version 1.0
 */
public interface HdResourceLoader {

    /**
     * 获取流u
     * @return
     * @throws IOException
     */
    InputStream getInputStream()throws IOException;
}
