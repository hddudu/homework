package com.hongdu.spring.beans.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @ClassName HdAbstractResouce
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/16 16:02
 * @Version 1.0
 */
public abstract class HdAbstractResouce implements HdResource {

    /**
     * 将获取流 的那个留给子类实现
     * @return
     * @throws IOException
     */
    @Override
    public URL getURL() throws IOException {
        return null;
    }

    @Override
    public File getFile() throws IOException {
        return null;
    }

    @Override
    public HdInputStreamResource createRelative(String relativePath) throws Exception {
        return null;
    }
}
