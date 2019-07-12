package com.hongdu.gupao.spring.iocbeanprocess.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName Resources
 * @Description Resource是spring内部定位资源的接口。
 * @Author dudu
 * @Date 2019/7/11 19:03
 * @Version 1.0
 */
public interface Resources {

    InputStream getInputStream() throws IOException;
}
