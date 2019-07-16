package com.hongdu.spring.beans.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @ClassName HdResource
 * @Description 从最开始 抽象化的流 --》 流转到了 资源 --》
 * @Author dudu
 * @Date 2019/7/16 15:59
 * @Version 1.0
 */
public interface HdResource extends HdInputStreamResource {
    //***********************资源属性

    /**
     * 获取 URL 资源定位符
     * @return
     * @throws IOException
     */
    URL getURL() throws IOException;

    /**
     * 获取文件
     * @return
     * @throws IOException
     */
    File getFile() throws IOException;

    /**
     * 根据路径获取流
     * @param relativePath
     * @return
     * @throws Exception
     */
    HdInputStreamResource createRelative(String relativePath) throws Exception;

}
