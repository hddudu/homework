package com.hongdu.gupao.spring.beans.io;

import com.hongdu.gupao.spring.iocbeanprocess.beans.io.ResourceLoader;
import com.hongdu.gupao.spring.iocbeanprocess.beans.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yihua.huang@dianping.com
 */
public class ResourceLoaderTest {

	@Test
	public void test() throws IOException {
		ResourceLoader resourceLoader = new ResourceLoader();
        Resources resource = resourceLoader.getResources("tinyioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
