package com.hongdu.gupao.spring.iocbeanprocess.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName URLResources
 * @Description URL 统一资源定位
 * @Author dudu
 * @Date 2019/7/11 19:04
 * @Version 1.0
 */
public class URLResources implements Resources {

    private final URL url;

    public URLResources(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
