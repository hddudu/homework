package com.hongdu.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName InputStreamResource
 * @Description 获取流的接口
 * @Author dudu
 * @Date 2019/7/16 15:58
 * @Version 1.0
 */
public interface HdInputStreamResource {

    /**
     * 获取资源流
     * @return
     * @throws IOException
     */
    InputStream getInputStrema()throws IOException;
}
